import React from 'react'
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom';
import { cheeseData } from "./_mocks_/mock_data";
import App from './App';

// mock fetch function
beforeEach(() => {
  global.fetch = jest.fn(() =>
    Promise.resolve({
      json: () =>
        Promise.resolve(cheeseData)
    })
  );
});

test('renders the correct number of cheese rows', async () => {
  render(<App />);
  // Wait for all the cheeses to be fetched
  await waitFor(() => expect(screen.getByText('Cabrales')).toBeInTheDocument());

  const cheeseRows = screen.getAllByRole('row').filter(row => row.parentElement.tagName !== 'THEAD');
  expect(cheeseRows).toHaveLength(cheeseData.length);
});

test('renders all cheeses in a table with respective details and buttons', () => {
  render(<App />);

  const cheeseRows = screen.getAllByRole('row').filter(row => row.parentElement.tagName !== 'THEAD');

  cheeseRows.forEach((row, index) => {
    const cheese = cheeses[index];

    expect(within(row).getByText(cheese.name)).toBeInTheDocument();
    expect(within(row).getByText('${cheese.pricePerKilo} / kg`')).toBeInTheDocument();
    expect(within(row).getByText(cheese.colour)).toBeInTheDocument();

    const button = within(row).getByRole('button', { name: "Calculate Price" });
    expect(button).toBeInTheDocument();
  });
});

test('opens offcanvas on button click', async () => {
  render(<App />);
  // Wait for all the cheeses to be fetched
  await waitFor(() => expect(screen.getByText('Cabrales')).toBeInTheDocument());
  const button = screen.getAllByRole('button', { name: 'Calculate Price' })[0];
  fireEvent.click(button);
  expect(screen.getByTestId("Offcanvas-calculator")).toBeVisible();
});

test('calculates correct price based on input weight', async () => {
  render(<App />);

  await waitFor(() => expect(screen.getByText('Cabrales')).toBeInTheDocument());

  const button = screen.getAllByRole('button', { name: 'Calculate Price' })[0];
  fireEvent.click(button);

  const input = screen.getByPlaceholderText('Enter kgs');
  fireEvent.change(input, { target: { value: '2' } });

  const calculatedPrice = 2 * cheeseData[0].pricePerKilo;
  expect(screen.getByText('Price: $' +  calculatedPrice.toFixed(2).toString())).toBeInTheDocument();
});

//TODO: Add more rigorous testing on offcanvas functionality