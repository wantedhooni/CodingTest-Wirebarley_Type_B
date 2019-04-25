# CodingTest-Wirebarley_Type_B
Wirebarley CodingTest Type_B

<h2>사용기술</h2>

- spring boot
- lombok

<h2>구동방법</h2>

```
git clone https://github.com/wantedhooni/CodingTest-Wirebarley_Type_B.git
cd CodingTest-Wirebarley_Type_B
cd wirebarleyTest
mvnw package
java -jar targe\target\wirebarley-0.0.1-SNAPSHOT.jar
```

브라우저에서 http://localhost:8080/ 접속

<h2>문제내용</h2>

- 송금국가는 미국, 호주 두 군데 중 하나를 select box로 선택합니다. 각각 통화는 USD, AUD 입니다.
- 수취국가는 한국, 일본, 필리핀 세 군데 중 하나를 select box로 선택합니다. 각각 통화는 KRW, JPN, PHP 입니다.
- 송금국가와 수취국가를 선택하면 아래 환율이 바뀌어나타나야 합니다. 환율은 1 USD/AUD 기준으로 각각 KRW, JPN, PHP의 대응 금액입니다.
- 송금액을 USD/AUD로 입력하고 Submit을 누르면 아래 다음과 같이 수취금액이 KRW, JPN, PHP 중 하나로 계산되어서 나와야 합니다.
- 환율과 수취금액은 소숫점 2째자리까지, 3자리 이상 되면 콤마를 가운데 찍어 보여줍니다. 예를 들어 1234라면 1,234.00으로 나타냅니다.
- 환율정보는 https://currencylayer.com/ 의 무료 서비스를 이용해서 실시간으로 가져와야 합니다. 웹 서버가 시작될 때 한번 가져와서 계속 사용해도 되고, 매번 새로운 환율 정보를 가져와도 됩니다. 
- 새로운 무료 계정을 등록해서 API 키를 받아서 사용하면 됩니다. 샘플로 등록된 계정의 키를 예를 들면 다음과 같습니다.
- 환율을 미리 계산해서 html/javascript 안에 넣어두고 수취국가를 변경할 때마다 이를 자바스크립트로 바로 가져와서 보여줘도 좋고,
- 혹은 매번 수취국가를 선택/변경할 때마다 API로 서버에 요청을 해서 환율정보를 가져오게 해도 좋습니다.
- Submit을 누르면 선택된 수취국가와 그 환율, 그리고 송금액을 가지고 수취금액을 계산해서 하단에 보여주면 됩니다.
API를 이용해서 서버에서 계산해서 뿌려도 되고
자바스크립트로 미리 가져온 환율을 계산해서 수취금액을 보여줘도 되고
Submit 버튼으로 폼을 submit해서 화면을 새로 그려도 됩니다.
- 수취금액을 입력하지 않거나, 0보다 작은 금액이거나 10,000 USD보다 큰 금액, 혹은 바른 숫자가 아니라면 “송금액이 바르지 않습니다"라는 에러 메시지를 보여줍니다. 메시지는 팝업, 혹은 하단에 빨간 글씨로 나타나면 됩니다.
