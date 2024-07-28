import React from "react";
import "./AnimalCard.css";
import {
  checkNumberAndAddPlural,
  convertDateToDdMmYyyy,
  API_URL,
} from "../../utils/util";

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
        src={`${API_URL}/foto/${imageUrl}`}
        alt={name}
      />
      <h2>{name}</h2>
      <p>{description}</p>
      <p>Categoria: {category}</p>
      <p>Data de Nascimento: {convertDateToDdMmYyyy(birthdate)}</p>
      <p>
        Idade: {idade} {checkNumberAndAddPlural(idade, "ano")}
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
