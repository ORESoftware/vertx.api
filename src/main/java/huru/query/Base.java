package huru.query;

class Base {
  
   interface IGetSQL {
    String getSQL();
  }
  
  interface IGetJoinName{
    String getJoinName();
  }
  
}
