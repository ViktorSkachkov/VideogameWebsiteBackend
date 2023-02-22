import {useEffect, useState} from "react";
import axios from "axios";
import VideogameCart from "./VideogameCart";
import AdditionCart from "./AdditionCart";

const Shop = () => {
    const [additions, setAdditions] = useState([]);
    useEffect(() => {
        getAdditions();
    });

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
                <div className="listOfAdditions">
                {additions.map((addition) => (
                    <AdditionCart addition={addition}/>
                ))}
                </div>
            </center>
        </>
    )
}
export default Shop;