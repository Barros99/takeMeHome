import React, { useState, ChangeEvent, FormEvent } from "react";
import axios from "axios";
import { apiUrl } from "../../utils/util";
import "./AnimalForm.css";

const AnimalForm: React.FC = () => {
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
    <div id="form">
      <form onSubmit={handleSubmit}>
        <input type="file" onChange={handleFileChange} required />
        <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
          placeholder="Nome"
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
          placeholder="Descrição"
          required
        />
        <div className="pick">
          <label htmlFor="status">Status:</label>
          <select
            value={status}
            onChange={(e) => setStatus(e.target.value)}
            required
          >
            <option value="DISPONIVEL">Disponível</option>
            <option value="ADOTADO">Adotado</option>
          </select>
        </div>
        <input
          type="text"
          value={category}
          onChange={(e) => setCategory(e.target.value)}
          placeholder="Categoria"
          required
        />
        <button type="submit">Enviar</button>
        <p>{message}</p>
      </form>
    </div>
  );
};

export default AnimalForm;
