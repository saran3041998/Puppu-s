function validateForm() {
	var titleName = document.menuItem.title.value;
	var priceValue = document.menuItem.price.value;
	var flag = 0;
	var regex = /^[a-z A-Z]*$/;
	var test = regex.test(titleName);
	var category = document.getElementById("category");
	if (category.value == "") {
		alert("Select one category");
		flag = 1;
		return false;
	}
	if (null == titleName || "" == titleName) {
		alert("Name cannot be empty");
		document.menuItem.title.focus();
		flag = 1;
		return false;
	}
	if (false == test) {
		alert("Name cannot have special characters");
		document.menuItem.title.focus();
		flag = 1;
		return false;
	}
	if (isNaN(priceValue)) {
		alert("Price has to be a number");
		document.menuItem.price.focus();
		flag = 1;
		return false;
	}
	if (titleName.length < 2 || titleName.length > 65) {
		alert("Title should have 2 to 65 characters");
		document.menuItem.title.focus();
		flag = 1;
		return false;
	}
	if (flag == 0)
		return true;
	return true;
}