import React from "react";

function AgentRecommendationCard({ recommendation }) {
  const flight = recommendation.recommendedFlight;

  return (
    <div style={styles.card}>
      <h2 style={styles.title}>FlyMate AI Recommendation</h2>

      <p style={styles.message}>{recommendation.message}</p>

      {flight !== null ? (
        <div>
          <h3 style={styles.airline}>
            {flight.airline} - {flight.flightNumber}
          </h3>

          <p>
            <strong>Route:</strong> {flight.source} to {flight.destination}
          </p>

          <p>
            <strong>Price:</strong> Rs. {flight.price}
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
        </div>
      ) : (
        <p>No flight recommendation available.</p>
      )}

      <div style={styles.reasonBox}>
        <strong>Reason:</strong>
        <p>{recommendation.reason}</p>
      </div>
    </div>
  );
}

const styles = {
  card: {
    backgroundColor: "#eff6ff",
    border: "2px solid #2563eb",
    padding: "20px",
    borderRadius: "14px",
    marginBottom: "30px",
    boxShadow: "0 4px 12px rgba(37, 99, 235, 0.2)",
  },
  title: {
    color: "#1d4ed8",
    marginBottom: "10px",
  },
  message: {
    fontWeight: "bold",
    color: "#334155",
  },
  airline: {
    color: "#1e40af",
  },
  reasonBox: {
    marginTop: "15px",
    padding: "12px",
    backgroundColor: "#ffffff",
    borderRadius: "10px",
    border: "1px solid #bfdbfe",
  },
};

export default AgentRecommendationCard;