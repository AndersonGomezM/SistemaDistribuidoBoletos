

function GET() {

    const [listaEventos, evento] = useState([])
    
    useEffect(() => {
        const fetchEventoData = async () => {
            try {
                const content = await fetch(Url + "boletos/", {
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

export default EventoSelected