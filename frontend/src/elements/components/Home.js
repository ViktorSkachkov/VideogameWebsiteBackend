import {useEffect, useState} from "react";
import axios from "axios";
import VideogameCard from "./VideogameCard";
import '../css/Home.css';
import {Link, useNavigate} from "react-router-dom";
import Cookies from "universal-cookie";

const Home = (updateUser) => {
    let navigate = useNavigate();
    const [featuredVideogames, setFeaturedVideogames] = useState([]);
    const [upcomingVideogames, setUpcomingVideogames] = useState([]);
    const [roles, setRoles] = useState([]);

    const cookies = new Cookies();
    const token = cookies.get("accessToken");

    useEffect(() => {
        getRoles();
        getVideogames();
    }, []);

    const getRoles = () => {
        let token_deserialized = JSON.parse(localStorage.getItem("token"));
        if(token_deserialized != null) {
            setRoles(token_deserialized.userRoles.map(element => element.role));
        }
    }

    const getVideogames = () => {
        var config1 = {
            method: "get",
            url: `http://localhost:8080/videogames/featured`,
            headers: {
                "Authorization": `Bearer ${token}`,
            },
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
            headers: {
                "Authorization": `Bearer ${token}`,
            },
        };
        axios(config1)
            .then(function (response) {
                setUpcomingVideogames(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    /*function updateProfile() {
        let token_deserialized = JSON.parse(localStorage.getItem("token"));
        const config = {
            headers: { Authorization: `Bearer ${token}` }
        };
        const bodyParams = {
            "id": token_deserialized.id,
            "username": token_deserialized.username,
            "pwd": token_deserialized.pwd,
            "email": token_deserialized.email,
            "bankAccount": "bankAccount",
            "userRoles": token_deserialized.userRoles,
        };
        axios.put(
            `http://localhost:8080/users`,
            bodyParams,
            config
        )
            .then(function (response) {
                changeSession();
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    function changeSession() {
        //e.preventDefault();
        let token_deserialized = JSON.parse(localStorage.getItem("token"));
        const cookies = new Cookies();
        var data = JSON.stringify({
            "username": token_deserialized.username,
            "password": "SomePassword"
        });

        var config = {
            method: 'post',
            url: 'http://localhost:8080/login',
            headers: {
                'Content-Type': 'application/json'
            },
            data: data
        };


        axios(config)
            .then(function (response) {
                cookies.set("accessToken", response.data.accessToken, { path: '/' });
                updateUser.updateUser();
            })
    }*/

        return (
            <>
                <center>
                    <img src="/69piR5.jpg" width="80%" height="400px"  alt="Currently the image can't load"/>
                </center>
                {roles.some(r => r == "CUSTOMER") ? <>
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