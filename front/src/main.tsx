import React from "react";
import ReactDOM from "react-dom/client";
import { Link, Route, BrowserRouter as Router, Routes } from "react-router-dom";
import About from "./components/About";
import Home from "./components/Home";
import AnimalForm from "./components/form/AnimalForm";
import AnimalList from "./components/list/AnimalList";
import "./index.css";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <Router>
      <nav>
        <ul>
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/about">About</Link>
          </li>
          <li>
            <Link to="/form">Form</Link>
          </li>
          <li>
            <Link to="/animals/">List</Link>
          </li>
        </ul>
      </nav>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/form" element={<AnimalForm />} />
        <Route path="/animals" element={<AnimalList />} />
        <Route path="*" element={<h1>Not Found</h1>} />
      </Routes>
    </Router>
  </React.StrictMode>
);
