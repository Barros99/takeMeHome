import React from "react";
import "./Footer.css";
import { siteName } from "../../utils/constants";

const Footer: React.FC = () => {
  return (
    <footer className="footer">
      <p>&copy; 2024 {siteName}. All rights reserved.</p>
      <p>
        Follow us on <a href="https://twitter.com">Twitter</a> and{" "}
        <a href="https://facebook.com">Facebook</a>.
      </p>
    </footer>
  );
};

export default Footer;
