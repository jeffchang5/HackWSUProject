// @flow

import React from 'react';
import styled from 'styled-components';

const AccountButton = styled.h1`
  font-size: 1.5em;
  text-align: center;
  color: #DF3232;
  font-family: Helvetica;
`;

const Title = styled.a`
  display: inline-block;
  min-height: 30px;
  padding: 25px 12%;
  width: 20%;
  text-decoration: none;
  font-size: 1.3em;
  color: white;
`;

const MenuButton = styled.h1`
  font-size: 1.5em;
  text-align: center;
  color: #DF3232;
  font-family: Tahoma;
`;
const MenuOptions = styled.div`
  position: absolute;
  padding-left: 15%;
  width: 60%;
  min-width: 650px;
  height: 100%;
  min-width: 300px;
  right: 2%;
`;
export default ({ links }: [{ title: string, url: string }]) => (
  <MenuOptions>
    {links.map(prop => (<Title href={prop.url}>{prop.title}</Title>))}
  </MenuOptions>
);


export { Title, MenuButton, AccountButton, MenuOptions };
