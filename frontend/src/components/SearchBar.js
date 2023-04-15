import React, { useEffect, useState } from 'react';
import { TextField, IconButton } from '@material-ui/core';
import SearchIcon from '@material-ui/icons/Search';
import axios from 'axios';

function SearchBar() {
  const [searchText, setSearchText] = useState('');

  const handleSearch = () => {
    console.log('Search button clicked with text: ', searchText);
    axios.get(`http://localhost:8080/restaurant/getAll`)
    .then((response) => {
      // Handle successful response from the server
      console.log(response.data);
    })
    .catch((error) => {
      // Handle error from the server
      console.error(error);
    });
  };

  const handleTextChange = (event) => {
    setSearchText(event.target.value);
  };

  return (
    <div>
      <TextField
        label="Search"
        value={searchText}
        onChange={handleTextChange}
      />
      <IconButton aria-label="search" onClick={handleSearch}>
        <SearchIcon />
      </IconButton>
    </div>
  );
}

export default SearchBar;