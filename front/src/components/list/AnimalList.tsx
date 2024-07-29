import React, { useEffect, useState } from "react";
import logoImage from "../../assets/logo.png";
import { apiUrl } from "../../utils/util";
import AnimalCard from "../card/AnimalCard";
import AnimalForm from "../form/AnimalForm";
import "./AnimalList.css";

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
  const [showCadastro, setShowCadastro] = useState(false);

  console.log("API_URL => ", apiUrl);

  useEffect(() => {
    const fetchAnimals = async () => {
      try {
        const response = await fetch(`${apiUrl}`);
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

      await fetch(`${apiUrl}/${id}/?status=${updatedAnimal?.status}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
      });
    } catch (error) {
      console.error("Error updating animal status:", error);
    }
  };

  const handleCadastroClick = () => {
    setShowCadastro(true);
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <>
      {/*<div className="wrap">*/}
      <div className="animal-list-header">
        <img src={logoImage} alt="Logo" className="logo" />
        <h2>TAKE ME HOME</h2>
        <nav>
          <ul>
            <li>
              <button onClick={handleCadastroClick}>Cadastrar</button>
            </li>
          </ul>
        </nav>
      </div>
      {/*</div>*/}
      {showCadastro ? (
        <AnimalForm />
      ) : (
        <div className="animal-list">
          {animals.map((animal) => (
            <AnimalCard
              key={animal.id}
              {...animal}
              toggleStatus={() => toggleStatus(animal.id)}
            />
          ))}
        </div>
      )}
      <footer className="footer">
        <div className="footer-section">
          <h3>About Us</h3>
          <ul>
            <li>
              <a href="#">Our Story</a>
            </li>
            <li>
              <a href="#">Our Team</a>
            </li>
            <li>
              <a href="#">Careers</a>
            </li>
          </ul>
        </div>
        <div className="footer-section">
          <h3>Products</h3>
          <ul>
            <li>
              <a href="#">Product 1</a>
            </li>
            <li>
              <a href="#">Product 2</a>
            </li>
            <li>
              <a href="#">Product 3</a>
            </li>
          </ul>
        </div>
        <div className="footer-section">
          <h3>Resources</h3>
          <ul>
            <li>
              <a href="#">Blog</a>
            </li>
            <li>
              <a href="#">Documentation</a>
            </li>
            <li>
              <a href="#">FAQ</a>
            </li>
          </ul>
        </div>
        <div className="footer-section">
          <h3>Connect</h3>
          <div className="social-icons">
            <a href="#">
              <i className="fab fa-facebook"></i>
            </a>
            <a href="#">
              <i className="fab fa-twitter"></i>
            </a>
            <a href="#">
              <i className="fab fa-instagram"></i>
            </a>
          </div>
        </div>
      </footer>
    </>
  );
};

export default AnimalList;
