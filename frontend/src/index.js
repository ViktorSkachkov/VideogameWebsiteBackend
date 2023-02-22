import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './elements/components/App';
import reportWebVitals from './reportWebVitals';
import {Link, NavLink, BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Home from "./elements/components/Home";
import Games from "./elements/components/Games";
import Shop from "./elements/components/Shop";
import News from "./elements/components/News";
import Support from "./elements/components/Support";
import Account from "./elements/components/Account";
import Navigation from "./elements/components/Navigation";
import Footer from "./elements/components/Footer";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
          <App/>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
