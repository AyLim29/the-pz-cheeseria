import React, { useEffect, useState } from 'react';
import { Button, Form, Input, Offcanvas, OffcanvasHeader, OffcanvasBody} from 'reactstrap';

import './App.css';

const App = () => {
  const [cheeses, setCheeses] = useState([]);
  const [selectedCheese, setSelectedCheese] = useState(null);
  const [showCalculator, setShowCalculator] = useState(false);
  const [calculatedPrice, setCalculatedPrice] = useState(0);
  const toggleCalculator = () => setShowCalculator(!showCalculator);
  const handleCheeseButton = (cheese) => {
    setShowCalculator(true);
    setSelectedCheese(cheese);
  };
  const handlePriceCalculation = (value) => {
    if (selectedCheese && value) {
      setCalculatedPrice(selectedCheese.pricePerKilo * (+value));
    } else {
      setCalculatedPrice(0);
    }
  };

  useEffect(() => {
    fetch('/api/getAllCheeses')
      .then(response => response.json())
      .then(data => setCheeses(data))
      .catch(error => console.error('Error fetching cheeses:', error));
  }, []);

  return (
    <div className="App">
      <Offcanvas 
        data-testid="Offcanvas-calculator"
        isOpen={showCalculator}
        direction="end"
        fade={false}
        toggle={toggleCalculator}
      >
        <OffcanvasHeader
          toggle={toggleCalculator}>
          <h2>Price Calculator</h2>
        </OffcanvasHeader>
        <OffcanvasBody>
          { selectedCheese ? (
            <div className='OffcanvasBodyText'>
            <strong>
              {selectedCheese.name}
            </strong>
            <p>${selectedCheese.pricePerKilo} / kg</p>
              <Form>
                <Input
                  type="number"
                  onChange={e => handlePriceCalculation(Number(e.target.value))}
                  placeholder='Enter kgs'
                /> 
              </Form>
              <h2>Price: ${parseFloat(calculatedPrice).toFixed(2)}</h2>
            </div>
          ) : (
            <p> select a cheese to see its details.</p>
          )}
        </OffcanvasBody>
      </Offcanvas>

      <h1>The PZ Cheeseria</h1>
      <table className='center'>
        <thead>
          <tr>
            <th></th>
            <th>Name</th>
            <th>Price Per Kilo</th>
            <th>Color</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {cheeses.map((cheese) => (
            <tr key={cheese.id}>
              <td>
                <img src = {cheese.imageURL} alt = ""></img>
              </td>
              <td>{cheese.name}</td>
              <td>${cheese.pricePerKilo} / kg</td>
              <td>{cheese.colour}</td>
              <td>
                <div>
                  <Button 
                    color="primary"
                    onClick={() => {
                        handlePriceCalculation();
                        handleCheeseButton(cheese);
                      }
                    }
                  >Calculate Price
                  </Button>
                </div>
                </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default App;
