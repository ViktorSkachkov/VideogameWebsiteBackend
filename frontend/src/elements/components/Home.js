import {useEffect, useState} from "react";
import axios from "axios";
import VideogameCard from "./VideogameCard";
import '../css/Home.css';
import {Link, useNavigate} from "react-router-dom";

const Home = (loggedUser) => {
    let navigate = useNavigate();
    const [featuredVideogames, setFeaturedVideogames] = useState([]);
    const [upcomingVideogames, setUpcomingVideogames] = useState([]);

    useEffect(() => {
        getVideogames();
    });

    const getVideogames = () => {
        var config1 = {
            method: "get",
            url: `http://localhost:8080/videogames/featured`,
            /*headers: {
                "Authorization": `Bearer ${token}`,
            },*/
        };
        axios(config1)
            .then(function (response) {
                setFeaturedVideogames(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });

        var config2 = {
            method: "get",
            url: `http://localhost:8080/videogames/upcoming`,
            /*headers: {
                "Authorization": `Bearer ${token}`,
            },*/
        };
        axios(config1)
            .then(function (response) {
                setUpcomingVideogames(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
    }
        return (
            <>
                <center>
                    <img src="/69piR5.jpg" width="80%" height="400px"  alt="Currently the image can't load"/>
                </center>
                {loggedUser.loggedUser != null ? <>
                    <h1 className="title">Featured Games</h1>
                    <div className="listOfGames">
                        {featuredVideogames.map((videogame) => (
                            <VideogameCard videogame={videogame} />
                        ))}
                    </div>
                    <h1 className="title">Upcoming Games</h1>
                    <div className="listOfGames">
                {upcomingVideogames.map((videogame) => (
                    <VideogameCard videogame={videogame} />
                    ))}
                    </div>
                    </> : <><br/><br/><br/><br/><br/><br/><br/><br/><br/>
                </>}

            </>
        )
}
export default Home;