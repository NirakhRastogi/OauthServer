import React from 'react';
import './App.css';
import {LoginComponent} from "./components/LoginComponent";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {SucessLoginComponent} from "./components/SucessLoginComponent";

function App() {

  return (
    <div>
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<LoginComponent />}></Route>
                <Route path="/success" element={<SucessLoginComponent />}></Route>
            </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
