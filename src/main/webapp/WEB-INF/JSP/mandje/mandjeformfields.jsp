<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<div class="user"><form:input path='naam' placeholder="Naam"
		autofocus='autofocus' required='required' maxlength='50' /></div>
<form:errors cssClass="error" path='naam' />

<div class="user"><form:input path='adres.straat'
		placeholder="Straat" required='required' maxlength='50' /></div>
<form:errors cssClass="error" path='adres.straat' />

<div class="user"><form:input path='adres.huisNr'
		placeholder="Huisnummer" required='required' maxlength='7' /></div>
<form:errors cssClass="error" path='adres.huisNr' />

<div class="user"><form:input path='adres.postcode'
		placeholder="Postcode" required='required' type='number' min='1000' max='9999' /></div>
<form:errors cssClass="error" path='adres.postcode' />

<div class="user"><form:input path='adres.gemeente'
		placeholder="Gemeente" required='required' maxlength='50' /></div>
<form:errors cssClass="error" path='adres.gemeente' />