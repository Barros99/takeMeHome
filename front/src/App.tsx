import React from "react";
import AnimalForm from "./components/form/AnimalForm";
import AnimalList from "./components/list/AnimalList";
import "./App.css";

const App: React.FC = () => {
  return (
    <div className="app">
      <h1>Animais Disponíveis para Adoção</h1>
      <AnimalForm />
      <AnimalList />
    </div>
  );
};
export default App;
