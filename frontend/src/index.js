import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { Map, GoogleApiWrapper, Marker } from 'google-maps-react';

const apiKey = process.env.REACT_APP_API_KEY;

const MapContainer = (props) => {
  const mapStyles = {
    width: '100%',
    height: '100%'
  };

  return (
    <Map
      google={props.google}
      zoom={14}
      style={mapStyles}
      initialCenter={{ lat: 49.2827, lng: -123.1207 }}
    >
      <Marker position={{ lat: 49.2827, lng: -123.1207 }} />
    </Map>
  );
};

const WrappedMap = GoogleApiWrapper({
  apiKey: apiKey
})(MapContainer);


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
    <WrappedMap />
  </React.StrictMode>
);

reportWebVitals();