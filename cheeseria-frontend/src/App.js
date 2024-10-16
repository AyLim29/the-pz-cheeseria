import React, { useEffect, useState } from 'react';

import './App.css';

const App = () => {
  const [cheeses, setCheeses] = useState([]);

  useEffect(() => {
    fetch('/api/getAllCheeses')
      .then(response => response.json())
      .then(data => setCheeses(data))
      .catch(error => console.error('Error fetching cheeses:', error));
  }, []);

  return (
    <div className="App">
      <h1>The PZ Cheeseria</h1>
      <table className='center'>
        <thead>
          <tr>
            <th></th>
            <th>Name</th>
            <th>Price Per Kilo</th>
            <th>Color</th>
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
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default App;
