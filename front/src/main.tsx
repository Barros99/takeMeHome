import React from "react";
import ReactDOM from "react-dom/client";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import AnimalList from "./components/list/AnimalList";
// import "./index.css";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <Router>
      <Routes>
        <Route path="/" element={<AnimalList />} />
        <Route path="*" element={<h1>Not Found</h1>} />
      </Routes>
    </Router>
  </React.StrictMode>
);
