#### JAVA 디자인 패턴(Design Pattern)

###### 싱글턴 패턴(Singleton Pattern)
 - 객체를 여러개 생성하여도 실제로는 객체 한개가 생성 된 것이다.
 - 해당 객체는 최초에 생성 된 객체이다.
 - 하나의 자원으로 모두가 공유하여 사용해야하는 경우에 사용한다.
 - 한개의 객체로만 사용하기 때문에 메모리 절약이 가능하다.
 - DBCP(DataBase Connection Pool) 떄 주로 많이 사용된다.
 
 console <br>
 == single ton == <br>
 1 <br>
 2 <br>
 3 <br>
 4 <br>
 == normal == <br>
 1 <br>
 1 <br>
 2 <br>
 2 <br>
 
 
###### 전략 패턴(Strategy Pattern)
 - 코드 중복을 방지한다. 
 - 런타임 시 타겟 메소드를 변경한다.
 - 확장성 및 알고리즘 변경이 용이하다.
 
 console <br>
 process 1 <br>
 process 2 <br>
 process 3 <br>
 process 4 <br>
 