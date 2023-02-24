import {useEffect, useState} from "react";
import axios from "axios";
import '../css/Games.css';
import VideogameCard from "./VideogameCard";
import VideogameCardAdmin from "./VideogameCardAdmin";
import {useNavigate} from "react-router-dom";

const Games = (loggedUser) => {
    const [videogames, setVideogames] = useState([]);
    let navigate = useNavigate();

    useEffect(() => {
        getVideogames();
    });

    const getVideogames = () => {
        var config = {
            method: "get",
            url: `http://localhost:8080/videogames`,
            /*headers: {
                "Authorization": `Bearer ${token}`,
            },*/
        };
        axios(config)
            .then(function (response) {
                setVideogames(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
    }
    return (
        <>
            <center>
                <img src="/69piR5.jpg" width="80%" height="400px"  alt="Currently the image can't load"/>
                <h1 className="title">GAMES</h1>
                <h3 className="title">Filters</h3>
            </center>
            {loggedUser.loggedUser != null ?
            <div className="listOfGames">
                {videogames.map((videogame) => (
                    <VideogameCard videogame={videogame} />
                ))}
            </div> : <center>
                    <div className="listOfGamesAdmin">
                        {videogames.map((videogame) => (
                            <VideogameCardAdmin videogame={videogame} />
                        ))}
                    </div>
                </center>}
            <center>
                <button onClick={() => {
                    navigate(`/addVideogame`, {
                    });
                }}>Add Game</button>
            </center><br/>
        </>
    )
}
export default Games;