import '../css/AdditionCart.css';
import {useNavigate} from "react-router-dom";

const AdditionCart = (addition) => {
    let navigate = useNavigate();

    return (
        <div className="additionCart"  onClick={() => {
            navigate(`/game/${addition.addition.id}`, {
            });
        }}>
            <div>
                <img src="69piR5.jpg" height="400px" width="450px" alt=""/>
            </div>
            <div className="">
                <center>
                    <h1>{addition.addition.name}</h1>
                </center>
            </div>
            <div className="lowerPart">
                <b><p className="price">Price: {addition.addition.price}</p></b>
                <p className="description">{addition.addition.description}</p>
            </div>
        </div>
    )
}
export default AdditionCart;