function login(users) {
	var emailInput = $("#emailInput").val();
	var passwordInput = $("#passwordInput").val();
	
	console.log(users);
	for (i = 0;i < users.length;i++) {
		var user = users[i];
		
		if (emailInput == user.email && passwordInput == user.password) {
			sessionStorage.setItem("loggedInId", user.id);
			if (user.role == "administrator") {
				window.location.replace("file:///home/rade/Documents/OSA/Vezbe06/web/administrator.html");
			} else if (user.role == "prodavac") {
				window.location.replace("file:///home/rade/Documents/OSA/Vezbe06/web/prodavac.html");
			} else if (user.role == "ponudjac") {
				window.location.replace("file:///home/rade/Documents/OSA/Vezbe06/web/ponudjac.html");
			}
		}
	}
	$("#message").text("Netacni podaci!");
}

$("document").ready(function() {
	$("#loginBtn").click(function(e) {
		$("#message").text("Netacni podaci!");
		e.preventDefault();
		$.ajax({
			url: "http://localhost:8080/api/users",
			dataType: "json",
			success: function(response) {
				login(response);
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
		console.log("test2");
	});
});