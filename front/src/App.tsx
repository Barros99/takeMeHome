import React from "react";
import AnimalForm from "./components/form/AnimalForm";
import AnimalList from "./components/list/AnimalList";
// import "./App.css";

const App: React.FC = () => {
  return (
    <div className="app">
      <AnimalList />
      <AnimalForm />
    </div>
  );
};
export default App;
