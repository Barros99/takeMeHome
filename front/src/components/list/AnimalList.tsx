import React, { useEffect, useState } from "react";
import AnimalCard from "../card/AnimalCard";
import "./AnimalList.css";
import { API_URL } from "../../utils/util";

interface Animal {
  id: number;
  imageUrl: string;
  name: string;
  description: string;
  category: string;
  birthdate: string;
  idade: number;
  status: string | boolean;
}

const AnimalList: React.FC = () => {
  const [animals, setAnimals] = useState<Animal[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchAnimals = async () => {
      try {
        const response = await fetch(`${API_URL}`);
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();

        setAnimals(data);
      } catch (error: any) {
        setError(error.message);
        console.error("Error fetching animals:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchAnimals();
  }, []);

  const toggleStatus = async (id: number) => {
    try {
      const updatedAnimals = animals.map((animal) =>
        animal.id === id
          ? {
              ...animal,
              status: animal.status === "ADOTADO" ? "DISPONIVEL" : "ADOTADO",
            }
          : animal
      );

      setAnimals(updatedAnimals);

      const updatedAnimal = updatedAnimals.find(
        (updatedAnimal) => updatedAnimal.id === id
      );

      await fetch(
        `${API_URL}/${id}/?status=${updatedAnimal?.status}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
    } catch (error) {
      console.error("Error updating animal status:", error);
    }
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <div className="animal-list">
      {animals.map((animal) => (
        <AnimalCard
          key={animal.id}
          {...animal}
          toggleStatus={() => toggleStatus(animal.id)}
        />
      ))}
    </div>
  );
};

export default AnimalList;
