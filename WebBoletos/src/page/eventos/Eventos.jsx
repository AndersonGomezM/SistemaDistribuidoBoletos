import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.min.js";
import "../eventos/Eventos.css"
import { useLocation, useParams } from "react-router-dom"
import { useState, useEffect } from 'react'
import { Carousel } from "bootstrap";

function Eventos() {
    return(
        <section class="hero-section" style="background-image: url(https://images.unsplash.com/photo-1557177324-56c542165309?ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80)">
            <div class="card-grid">
                <a class="card" href="#">
                    <div class="card__background">
                        <div class="card-grid">
                            <a class="card" href="#">
                                <div class="card__background" />
                                <div class="card__content">
                                    <p class="card__category">sss</p>
                                    <h3 class="card__heading">sssss</h3>
                                </div>
                            </a>
                        </div>
                    </div>
                </a>
            </div>
        </section>
    )
}

function GET() {

    const [listaEventos, evento] = useState([])
    
    useEffect(() => {
        const fetchEventoData = async () => {
            try {
                const content = await fetch(Url + "eventos/", {
                    method: "GET",
                    headers: {
                        'Content-Type': 'application/json',
                    },
                })
        
                const response = await content.json()
                evento(response)
    
            } catch (error) {
                console.log(error)
            }
        }

        fetchEventoData()
    
    }, [])

    return(
        listaEventos
    )
}

export default Eventos