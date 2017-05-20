import React from 'react';
import styled from 'styled-components';
import LoginWrapper from './wrapper';
import SunBorder from './border';


const Container = styled.div`
  position: relative;
  top: 6%;
  padding-top: 90px;
`


export default () => (
  <Container>
    <SunBorder>
      <LoginWrapper>
      </LoginWrapper>
    </SunBorder>
  </Container>
);
