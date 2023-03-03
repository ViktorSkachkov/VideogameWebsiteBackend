import {useEffect, useState} from "react";
import axios from "axios";
import '../css/Shop.css';
import AdditionCard from "./AdditionCard";
import AdditionCardAdmin from "./AdditionCardAdmin";
import {useNavigate} from "react-router-dom";

const Shop = (loggedUser) => {
    const [additions, setAdditions] = useState([]);
    let navigate = useNavigate();

    useEffect(() => {
        getAdditions();
    }, []);

    const getAdditions = () => {
        var config = {
            method: "get",
            url: `http://localhost:8080/additions`,
            /*headers: {
                "Authorization": `Bearer ${token}`,
            },*/
        };
        axios(config)
            .then(function (response) {
                setAdditions(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
    }
    return (
        <>
            <center>
                <h1 className="title">ADDITIONS</h1>
                <h3 className="title">Filters</h3>
                    {loggedUser.loggedUser != null ?
                        <div className="listOfAdditions">
                        {
                            additions.map((addition) => (
                                <AdditionCard addition={addition}/>
                            ))
                        }
                        </div> : <> <div className="listOfAdditionsAdmin">
                            {
                                additions.map((addition) => (
                                    <AdditionCardAdmin addition={addition}/>
                                ))
                            }
                        </div>
                        <button onClick={() => {
                        navigate(`/addAddition`, {
                    });
                    }}>Add Addition</button></>
                    }
            </center><br/>
        </>
    )
}
export default Shop;