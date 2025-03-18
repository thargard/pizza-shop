import {useState} from "react";

interface NumberInputProps {
    value: number;
    setValue: (newValue: number) => void;
}

const NumberInput: React.FC<NumberInputProps> = ({ value, setValue }) => {
    const increment = () => setValue(value + 1);
    const decrement = () => setValue(value > 0 ? value - 1 : 0);

    return(
        <div>
            <button className="btn" onClick={decrement}>-</button>
            <input type="number" name="amount" value={value} onChange={(e) => setValue(parseInt(e.target.value) || 0)}/>
            <button className="btn" onClick={increment}>+</button>
        </div>
    );
};

export default NumberInput;