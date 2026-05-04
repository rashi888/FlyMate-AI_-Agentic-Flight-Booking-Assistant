import React from "react";

function FlightCard({ flight }) {
  return (
    <div style={styles.card}>
      <h3 style={styles.airline}>{flight.airline}</h3>

      <p>
        <strong>Flight No:</strong> {flight.flightNumber}
      </p>

      <p>
        <strong>Route:</strong> {flight.source} to {flight.destination}
      </p>

      <p>
        <strong>Departure:</strong> {flight.departureTime}
      </p>

      <p>
        <strong>Arrival:</strong> {flight.arrivalTime}
      </p>

      <p>
        <strong>Duration:</strong> {flight.durationMinutes} minutes
      </p>

      <p>
        <strong>Stops:</strong> {flight.stops}
      </p>

      <p>
        <strong>Available Seats:</strong> {flight.availableSeats}
      </p>

      <h2 style={styles.price}>Rs. {flight.price}</h2>

      <button type="button" style={styles.button}>
        Book Now
      </button>
    </div>
  );
}

const styles = {
  card: {
    backgroundColor: "#ffffff",
    padding: "20px",
    borderRadius: "12px",
    boxShadow: "0 4px 12px rgba(0, 0, 0, 0.1)",
    border: "1px solid #e5e7eb",
  },
  airline: {
    color: "#2563eb",
    marginBottom: "10px",
  },
  price: {
    color: "#16a34a",
    marginTop: "15px",
  },
  button: {
    width: "100%",
    padding: "10px",
    borderRadius: "8px",
    border: "none",
    backgroundColor: "#16a34a",
    color: "#ffffff",
    fontWeight: "bold",
    cursor: "pointer",
  },
};

export default FlightCard;
