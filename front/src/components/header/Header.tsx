import React from "react";
import { Link } from "react-router-dom";
import "./Header.css";
import logoImage from "../../assets/logo.png";
import { listPets, petRegister, siteName } from "../../utils/constants";

const Header: React.FC = () => {
  return (
    <header className="header">
      <h1>{siteName}</h1>
      <img src={logoImage} alt="Logo" className="logo" />
      <nav>
        <ul>
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/about">About</Link>
          </li>
          <li>
            <Link to="/contact">Contact</Link>
          </li>
          <li>
            <Link to="/register">{petRegister}</Link>{" "}
          </li>
          <li>
            <Link to="/list">{listPets}</Link>{" "}
          </li>
        </ul>
      </nav>
    </header>
  );
};

export default Header;
