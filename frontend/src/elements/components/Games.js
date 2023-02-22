import {useEffect, useState} from "react";
import axios from "axios";
import VideogameCart from "./VideogameCart";

const Games = () => {
    const [videogames, setVideogames] = useState([]);

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
            <div className="listOfGames">
                {videogames.map((videogame) => (
                    <VideogameCart videogame={videogame} />
                ))}
            </div>
        </>
    )
}
export default Games;