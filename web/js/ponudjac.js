var AuctionManager = {
	basePath : function() { return 'http://localhost:8080/api/auctions'; },
	
	loadAuctions : function() {
		$.ajax({
			url: this.basePath(),
			dataType: "json",
			success: function(response) {
				var sorted = response.sort(function(a, b) {
					return b.startDate < a.startDate ? 1
						: b.startDate > a.startDate ? -1
						: 0;
				});
				
				var header = ("<th>" +
						"<td>Pocetna cena</td>" +
						"<td>Pocetni datum</td>" +
						"<td>Krajnji datum</td>" +
						"<td>Zapoceo</td>" +
						"<td>Predmet</td>" +
						"<td>Izmeni</td>" +
						"</th>");
				
				$("#auctionTable").append(header);
				
				for (var i = 0;i < sorted.length;i++) {
					auction = sorted[i];					
					auctionTr = $("<tr></tr>");

					indexTd = $("<td>" + (i + 1) + "</td>");
					startPriceTd = $("<td></td>");
					startPriceTd.append(auction.startPrice);
					startDateTd = $("<td></td>");
					if (auction.startDate != null) {
						startDateTd.append(new Date(auction.startDate).toDateString());
					} else {
						startDateTd.append("Nije pocela");
					}
					endDateTd = $("<td></td>");
					if (auction.endDate != null) {
						endDateTd.append(new Date(auction.endDate).toDateString());
					} else {
						endDateTd.append("Nije zavrsena");
					}
					
					userTd = $("<td>" + auction.user.name + "</td>");
					itemTd = $("<td>" + auction.item.name + "</td>");

					editTd = $("<td></td>");
					editLink = $('<a href=\'javascript:AuctionManager.addBid(' + auction.id + ')\'>Nova ponuda</a>');
					editTd.append(editLink);

					auctionTr.append(indexTd);
					auctionTr.append(startPriceTd);
					auctionTr.append(startDateTd);
					auctionTr.append(startDateTd);
					auctionTr.append(endDateTd);
					auctionTr.append(userTd);
					auctionTr.append(itemTd);
					auctionTr.append(editTd);

					$("#auctionTable").append(auctionTr);
				}
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
	},
	
	addBid : function(auctionId) {
		$("#auctionId").val(auctionId);
		$("#userId").val(sessionStorage.getItem("loggedInId"));
		$("#newBidDiv").show();
	}
};

$("document").ready(function() {
	$("#personalDataForm").hide();
	$("#passwordForm").hide();
	$("#newBidDiv").hide();
	AuctionManager.loadAuctions();
	
	$("#editPersonalData").click(function (e) {
		e.preventDefault();
		$("#personalDataForm").show();
		$("#editPersonalData").hide();
		$.ajax({
			url: "http://localhost:8080/api/users/" + sessionStorage.getItem("loggedInId"),
			dataType: "json",
			success: function(response) {
				user = response;
				$("#personalNameInput").val(user.name);
				$("#personalAddressInput").val(user.address);
				$("#personalEmailInput").val(user.email);
				$("#personalPhoneInput").val(user.phone);
				$("#personalUserId").val(user.id);
				$("#personalRole").val(user.role);
				$("#personalPassword").val(user.password);
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
	});
	
	$("#personalSaveButton").click(function (e) {
		e.preventDefault();
		var editUser = JSON.stringify({
			"id" : $("#personalUserId").val(),
			"name" : $("#personalNameInput").val(),
			"email" : $("#personalEmailInput").val(),
			"password" : $("#personalPassword").val(),
			"address" : $("#personalAddressInput").val(),
			"phone" : $("#personalPhoneInput").val(),
			"picture" : "",
			"role" : $("#personalRole").val()
		});

		$.ajax({
			url: "http://localhost:8080/api/users/" + sessionStorage.getItem("loggedInId"),
			dataType: "json",
			method: "PUT",
			contentType: "application/json",
			data: editUser,
			success: function(response) {
				location.reload();
			},
			error: function(request, options, error) {
				console.log(error);
				console.log(editUser);
			}
		});
		
		$("#personalDataForm").hide();
	});
	
	$("#editPassword").click(function (e) {
		e.preventDefault();
		$("#passwordForm").show();
		$("#editPassword").hide();
		$.ajax({
			url: "http://localhost:8080/api/users/" + sessionStorage.getItem("loggedInId"),
			dataType: "json",
			success: function(response) {
				user = response;
				$("#personalNameInput").val(user.name);
				$("#personalAddressInput").val(user.address);
				$("#personalEmailInput").val(user.email);
				$("#personalPhoneInput").val(user.phone);
				$("#personalUserId").val(user.id);
				$("#personalRole").val(user.role);
				$("#personalPassword").val(user.password);
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
	});
	
	$("#saveEditPassword").click(function (e) {
		e.preventDefault();
		var editUser = JSON.stringify({
			"id" : $("#personalUserId").val(),
			"name" : $("#personalNameInput").val(),
			"email" : $("#personalEmailInput").val(),
			"password" : $("#newPassword").val(),
			"address" : $("#personalAddressInput").val(),
			"phone" : $("#personalPhoneInput").val(),
			"picture" : "",
			"role" : $("#personalRole").val()
		});

		$.ajax({
			url: "http://localhost:8080/api/users/" + sessionStorage.getItem("loggedInId"),
			dataType: "json",
			method: "PUT",
			contentType: "application/json",
			data: editUser,
			success: function(response) {
				location.reload();
				console.log(editUser);
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
		$("#passwordForm").show();
		$("#editPassword").hide();
	});
	
	$("#submit").click(function (e) {
		window.location.replace("file:///home/rade/Documents/OSA/Vezbe06/web/ponudjac.html");
	});
	
	$("#logout").click(function (e) {
		sessionStorage.setItem("loggedInId", null);
		window.location.replace("file:///home/rade/Documents/OSA/Vezbe06/web/login.html");
	});
});