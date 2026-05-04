import { useState } from "react";
import axios from "axios";

function SearchFlights() {
  const [source, setSource] = useState("");
  const [destination, setDestination] = useState("");
  const [flights, setFlights] = useState([]);
  const [message, setMessage] = useState("");

  const searchFlights = async () => {
    if (source === "" || destination === "") {
      setMessage("Please enter source and destination");
      return;
    }

    try {
      setMessage("");

      const response = await axios.get(
        "http://localhost:8080/api/flights/search",
        {
          params: {
            source: source,
            destination: destination,
          },
        },
      );

      setFlights(response.data);

      if (response.data.length === 0) {
        setMessage("No flights found");
      }
    } catch (error) {
      console.log(error);
      setMessage("Something went wrong");
    }
  };

  return (
    <div style={{ padding: "30px", fontFamily: "Arial" }}>
      <h1>FlyMate AI - Search Flights</h1>

      <div style={{ marginBottom: "20px" }}>
        <input
          type="text"
          placeholder="Enter source"
          value={source}
          onChange={(e) => setSource(e.target.value)}
          style={{
            padding: "10px",
            marginRight: "10px",
          }}
        />

        <input
          type="text"
          placeholder="Enter destination"
          value={destination}
          onChange={(e) => setDestination(e.target.value)}
          style={{
            padding: "10px",
            marginRight: "10px",
          }}
        />

        <button onClick={searchFlights} style={{ padding: "10px" }}>
          Search
        </button>
      </div>

      {message && <p>{message}</p>}

      <div>
        {flights.map((flight) => (
          <div
            key={flight.id}
            style={{
              border: "1px solid #ccc",
              padding: "15px",
              marginBottom: "15px",
              borderRadius: "8px",
            }}
          >
            <h3>{flight.airline}</h3>
            <p>Flight Number: {flight.flightNumber}</p>
            <p>
              Route: {flight.source} to {flight.destination}
            </p>
            <p>Departure: {flight.departureTime}</p>
            <p>Arrival: {flight.arrivalTime}</p>
            <p>Duration: {flight.durationMinutes} minutes</p>
            <p>Price: ₹{flight.price}</p>
            <p>Available Seats: {flight.availableSeats}</p>
            <p>Stops: {flight.stops}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default SearchFlights;
