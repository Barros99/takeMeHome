import axios from "axios";
import React, { ChangeEvent, FormEvent, useState } from "react";
import {
    adopted,
    available,
    Category,
    Description,
    Name,
    send,
} from "../../utils/constants";
import { apiUrl } from "../../utils/util";
import "./AnimalForm.css";

const Register: React.FC = () => {
  const [foto, setFoto] = useState<File | null>(null);
  const [name, setName] = useState<string>("");
  const [birthdate, setBirthdate] = useState<string>("");
  const [description, setDescription] = useState<string>("");
  const [status, setStatus] = useState<string>("DISPONIVEL");
  const [category, setCategory] = useState<string>("");
  const [message, setMessage] = useState<string>("");

  const handleFileChange = (e: ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setFoto(e.target.files[0]);
    }
  };

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (!foto) return;

    const formData = new FormData();
    formData.append("foto", foto);
    formData.append("name", name);
    formData.append("birthdate", birthdate);
    formData.append("description", description);
    formData.append("status", status);
    formData.append("category", category);

    try {
      const response = await axios.post(`${apiUrl}/upload`, formData);
      setMessage(response.data);
    } catch (error) {
      setMessage("Erro ao enviar a imagem.");
    }
  };

  return (
      <form onSubmit={handleSubmit}>
        <input
          type="file"
          onChange={handleFileChange}
          required
          accept=".jpg, .jpeg, .png"
          lang="en"
        />
        <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
          placeholder={Name}
          required
        />
        <input
          type="date"
          value={birthdate}
          onChange={(e) => setBirthdate(e.target.value)}
          required
        />
        <textarea
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          placeholder={Description}
          required
        />
        <div className="pick">
          <label htmlFor="status">Status:</label>
          <select
            value={status}
            onChange={(e) => setStatus(e.target.value)}
            required
          >
            <option value="DISPONIVEL">{available}</option>
            <option value="ADOTADO">{adopted}</option>
          </select>
        </div>
        <input
          type="text"
          value={category}
          onChange={(e) => setCategory(e.target.value)}
          placeholder={Category}
          required
        />
        <button type="submit">{send}</button>
        <p>{message}</p>
      </form>
  );
};

export default Register;
