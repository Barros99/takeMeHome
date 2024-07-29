import React from "react";
import {
  apiUrl,
  convertDateToDdMmYyyy
} from "../../utils/util";
import "./AnimalCard.css";
import { BirthDate, Category } from "../../utils/constants";

interface AnimalCardProps {
  imageUrl: string;
  name: string;
  description: string;
  category: string;
  birthdate: string;
  idade: number;
  status: string | boolean;
  toggleStatus: () => void;
}

const AnimalCard: React.FC<AnimalCardProps> = ({
  imageUrl,
  name,
  description,
  category,
  birthdate,
  idade,
  status,
  toggleStatus,
}) => {

  function isAdopted(status: string | boolean): boolean {
    return status === "ADOTADO";
  }

  return (
    <div className="animal-card">
      <img
        src={`${apiUrl}/foto/${imageUrl}`}
        alt={name}
      />
      <h2>{name}</h2>
      <p>{description}</p>
      <p>{Category}: {category}</p>
      <p>{BirthDate}: {convertDateToDdMmYyyy(birthdate)}</p>
      <p>
        Idade: {idade}
      </p>
      <label>
        Status: {isAdopted(status) ? "ADOTADO" : "DISPONIVEL"}
        <input
          type="checkbox"
          checked={isAdopted(status)}
          onChange={toggleStatus}
        />
      </label>
    </div>
  );
};

export default AnimalCard;
