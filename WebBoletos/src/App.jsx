import { useState } from 'react'
import "bootstrap/dist/css/bootstrap.min.css";
import {Router, Routes, Route} from "react-router-dom"
import Eventos from '../page/eventos/Eventos';
import EventoSelected from './page/eventos_selected/EventoSelected';

function App() {
  return (
    <div>
      <header>
        <Eventos/>
      </header>
      
      <Routes>
        <Route>
          <Route path="/Eventos" element={ <Eventos/> }/>
          <Route path="/EventoSelected/:id"  component={ <EventoSelected/> } element={ <EventoSelected/> }/>
        </Route>
      </Routes>
    </div>
  )
}

export default App