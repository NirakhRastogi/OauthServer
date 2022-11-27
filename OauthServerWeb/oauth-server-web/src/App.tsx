import React from 'react';
import './App.css';
import {LoginComponent} from "./components/LoginComponent";
import {BrowserRouter, Route, Routes} from "react-router-dom";

function App() {

  return (
    <div>
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<LoginComponent />}></Route>
            </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
