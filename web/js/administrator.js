var UserManager = {
	basePath : function() { return 'http://localhost:8080/api/users'; },
	
	loadUsers : function() {
		$.ajax({
			url: "http://localhost:8080/api/users",
			dataType: "json",
			success: function(response) {
				for (var i = 0;i < response.length;i++) {
					user = response[i];					
					userTr = $("<tr></tr>");

					indexTd = $("<td>" + (i + 1) + "</td>");
					idTd = $("<td></td>");
					idTd.text(user.id);
					idTd.attr("style", "display:none");
					nameTd = $("<td></td>");
					nameTd.text(user.name);
					emailTd = $("<td></td>");
					emailTd.text(user.email);

					editTd = $("<td></td>");
					editLink = $('<a href=\'javascript:UserManager.editUser(' + user.id + ')\'>Izmeni</a>');
					editTd.append(editLink);
					
					deleteTd = $("<td></td>");
					deleteLink = $('<a href=\'javascript:UserManager.deleteUser("' + user.id + '")\'>Obrisi</a>');
					deleteTd.append(deleteLink);

					userTr.append(indexTd);
					userTr.append(idTd);
					userTr.append(nameTd);
					userTr.append(emailTd);
					userTr.append(editTd);
					userTr.append(deleteTd);

					$("#userTable").append(userTr);
				}
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
	},

	saveUser : function(userId) {
		var newUser = JSON.stringify({
			"name" : $("#nameInput").val(),
			"email" : $("#emailInput").val(),
			"password" : $("#passwordInput").val(),
			"address" : $("#addressInput").val(),
			"phone" : $("#phoneInput").val(),
			"picture" : $("#pictureInput").val(),
			"role" : $("#roleInput").val()
		});

		$.ajax({
			url: UserManager.basePath(),
			dataType: "json",
			method: "POST",
			contentType: "application/json",
			data: newUser,
			success: function(response) {
				$("#nameInput").val("");
				$("#emailInput").val("");
				$("#passwordInput").val("");
				$("#addressInput").val("");
				$("#phoneInput").val("");
				$("#pictureInput").val("");
				$("#roleInput").val("");
				location.reload();
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
		
		$("#addNewForm").hide();
	},
	
	editUser : function(userId) {
		$.ajax({
			url: "http://localhost:8080/api/users/" + userId,
			dataType: "json",
			method: "GET",
			success: function(response) {
				var user = response;
				$("#userId").val(userId);
				$("#nameEditInput").val(user.name);
				$("#addressEditInput").val(user.address);
				$("#emailEditInput").val(user.email);
				$("#passwordEditInput").val(user.password);
				$("#phoneEditInput").val(user.phone);
				$("#roleEditInput").val(user.role);
				$("#editForm").show();
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
	},

	saveEditUser : function() {
		var userId = $("#userId").val();
		var editUser = JSON.stringify({
			"name" : $("#nameEditInput").val(),
			"email" : $("#emailEditInput").val(),
			"password" : $("#passwordEditInput").val(),
			"address" : $("#addressEditInput").val(),
			"phone" : $("#phoneEditInput").val(),
			"picture" : $("#pictureEditInput").val(),
			"role" : $("#roleEditInput").val()
		});

		$("#userTable tr").remove();

		$.ajax({
			url: UserManager.basePath() + "/" + userId,
			dataType: "json",
			method: "PUT",
			contentType: "application/json",
			data: editUser,
			success: function(response) {
				location.reload();
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
		
		$("#editForm").hide();
	},
	
	deleteUser(userId) {
		$.ajax({
			url: "http://localhost:8080/api/users/" + userId,
			method: "DELETE",
			success: function(response) {
				location.reload();
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
	},
	
	hideAddForm() {
		$("#addNewForm").hide();
	},
	
	hideEditForm() {
		$("#editForm").hide();
	}
};

var ItemManager = {
	basePath : function() { return 'http://localhost:8080/api/items'; },
	
	findItem : function (itemId) {
		$.ajax({
			url: this.basePath() + itemId,
			dataType: "json",
			method: "GET",
			success: function(response) {
				return response[0];
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
	},
	
	loadItems : function() {
		$.ajax({
			url: this.basePath(),
			dataType: "json",
			success: function(response) {
				for (var i = 0;i < response.length;i++) {
					item = response[i];					
					itemTr = $("<tr></tr>");

					indexTd = $("<td>" + (i + 1) + "</td>");
					nameTd = $("<td></td>");
					nameTd.append(item.name);
					descriptionTd = $("<td></td>");
					descriptionTd.append(item.description);

					editTd = $("<td></td>");
					editLink = $('<a href=\'javascript:ItemManager.editItem(' + item.id + ')\'>Izmeni</a>');
					editTd.append(editLink);
					
					deleteTd = $("<td></td>");
					deleteLink = $('<a href=\'javascript:ItemManager.deleteItem("' + item.id + '")\'>Obrisi</a>');
					deleteTd.append(deleteLink);

					itemTr.append(indexTd);
					itemTr.append(nameTd);
					itemTr.append(descriptionTd);
					itemTr.append(editTd);
					itemTr.append(deleteTd);

					$("#itemTable").append(itemTr);
				}
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
	},
	
	saveItem : function() {
		var newUser = JSON.stringify({
			"name" : $("#itemNameInput").val(),
			"description" : $("#itemDescriptionInput").val(),
			"picture" : "",
			"sold" : false
		});

		$.ajax({
			url: this.basePath(),
			dataType: "json",
			method: "POST",
			contentType: "application/json",
			data: newUser,
			success: function(response) {
				$("#itemNameInput").val("");
				$("#itemDescriptionInput").val("");
				location.reload();
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
		
		$("#addNewForm").hide();
	},
	
	editItem : function(itemId) {
		$.ajax({
			url: this.basePath() + "/" + itemId,
			dataType: "json",
			method: "GET",
			success: function(response) {
				var item = response;
				$("#itemId").val(itemId);
				$("#editItemNameInput").val(item.name);
				$("#editItemDescriptionInput").val(item.description);
				$("#editItemForm").show();
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
	},

	saveEditItem : function() {
		var itemId = $("#itemId").val();
		var editItem = JSON.stringify({
			"name" : $("#editItemNameInput").val(),
			"description" : $("#editItemDescriptionInput").val(),
			"picture" : "",
			"sold" : false
		});

		$("#itemTable tr").remove();

		$.ajax({
			url: ItemManager.basePath() + "/" + itemId,
			dataType: "json",
			method: "PUT",
			contentType: "application/json",
			data: editItem,
			success: function(response) {
				location.reload();
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
		
		$("#editItemForm").hide();
	},
	
	deleteItem(itemId) {
		$.ajax({
			url: this.basePath() + "/" + itemId,
			method: "DELETE",
			success: function(response) {
				location.reload();
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
	},
};

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
						"<td>Obrisi</td>" +
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
					editLink = $('<a href=\'javascript:AuctionManager.editAuction(' + auction.id + ')\'>Izmeni</a>');
					editTd.append(editLink);
					
					deleteTd = $("<td></td>");
					deleteLink = $('<a href=\'javascript:AuctionManager.deleteAuction("' + auction.id + '")\'>Obrisi</a>');
					deleteTd.append(deleteLink);

					auctionTr.append(indexTd);
					auctionTr.append(startPriceTd);
					auctionTr.append(startDateTd);
					auctionTr.append(startDateTd);
					auctionTr.append(endDateTd);
					auctionTr.append(userTd);
					auctionTr.append(itemTd);
					auctionTr.append(editTd);
					auctionTr.append(deleteTd);

					$("#auctionTable").append(auctionTr);
				}
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
	},
	
	saveAuction : function() {
		var newAuction = JSON.stringify({
			"startPrice" : $("#startPriceInput").val(),
			"startDate" : $("#startDateInput").val(),
			"endDate" : $("#endDateInput").val(),
			"user" : $("#auctionUserTxt").data("user"),
			"item" : $("#auctionItemTxt").data("item")
		});

		console.log(newAuction);
		
		$.ajax({
			url: this.basePath(),
			dataType: "json",
			method: "POST",
			contentType: "application/json",
			data: newAuction,
			success: function(response) {
				location.reload();
			},
			error: function(request, options, error) {
				console.log(error);
				console.log(newAuction);
			}
		});
		
		$("#newAuctionForm").hide();
	},
	
	editAuction : function(auctionId) {
		$.ajax({
			url: this.basePath() + "/" + auctionId,
			dataType: "json",
			method: "GET",
			success: function(response) {
				var auction = response;
				$("#auctionId").val(auctionId);
				$("#editStartPriceInput").val(auction.startPrice);
				$("#editStartDateInput").val(auction.startDate);
				$("#editEndDateInput").val(auction.endDate);
				$("#editAuctionUserTxt").data("user", auction.user);
				$("#editAuctionItemTxt").data("item", auction.item);
				$("#editAuctionForm").show();
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
		
		$.ajax({
			url: "http://localhost:8080/api/users",
			dataType: "json",
			success: function(response) {
				for (i = 0;i < response.length;i++) {
					var user = response[i];
					$("#editAuctionUserList").append("<li><a href='javascript:AuctionManager.setEditAuctionUser(" + user.id + ")'>" + user.name + "</a></li>");
				}
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
		
		$.ajax({
			url: "http://localhost:8080/api/items",
			dataType: "json",
			success: function(response) {
				for (i = 0;i < response.length;i++) {
					var item = response[i];
					$("#editAuctionItemsList").append("<li><a href='javascript:AuctionManager.setEditAuctionItem(" + item.id + ")'>" + item.name + "</a></li>");
				}
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
	},
	
	setEditAuctionUser : function(userId) {
		$.ajax({
			url: "http://localhost:8080/api/users/" + userId,
			dataType: "json",
			method: "GET",
			success: function(response) {
				$("#editAuctionUserTxt").data("user", response);
				console.log(response);
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
	},
	
	setEditAuctionItem : function(itemId) {
		$.ajax({
			url: "http://localhost:8080/api/items/" + itemId,
			dataType: "json",
			method: "GET",
			success: function(response) {
				$("#editAuctionItemTxt").data("item", response);
				console.log(response);
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
	},
	
	setAuctionUser : function(userId) {
		$.ajax({
			url: "http://localhost:8080/api/users/" + userId,
			dataType: "json",
			method: "GET",
			success: function(response) {
				$("#auctionUserTxt").data("user", response);
				console.log(response);
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
	},
	
	setAuctionItem : function(itemId) {
		$.ajax({
			url: "http://localhost:8080/api/items/" + itemId,
			dataType: "json",
			method: "GET",
			success: function(response) {
				$("#auctionItemTxt").data("item", response);
				console.log(response);
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
	},

	saveEditAuction : function() {
		var auctionId = $("#auctionId").val();
		var editAuction = JSON.stringify({
			"startPrice" : $("#editStartPriceInput").val(),
			"startDate" : $("#editStartDateInput").val(),
			"endDate" : $("#editEndDateInput").val(),
			"item" : $("#editAuctionItemTxt").data("item"),
			"user" : $("#editAuctionUserTxt").data("user")
		});

		console.log(editAuction);

		$.ajax({
			url: this.basePath() + "/" + auctionId,
			dataType: "json",
			method: "PUT",
			contentType: "application/json",
			data: editAuction,
			success: function(response) {
				location.reload();
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
		
		$("#editAuctionForm").hide();
	},
	
	deleteAuction(auctionId) {
		$.ajax({
			url: this.basePath() + "/" + auctionId,
			method: "DELETE",
			success: function(response) {
				location.reload();
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
	}
};

var BidManager = {
	basePath : function() { return 'http://localhost:8080/api/bids'; },
	
	loadBids : function() {
		$.ajax({
			url: this.basePath(),
			dataType: "json",
			success: function(response) {
				/*var header = ("<th>" +
						"<td>Pocetna cena</td>" +
						"<td>Pocetni datum</td>" +
						"<td>Krajnji datum</td>" +
						"<td>Zapoceo</td>" +
						"<td>Predmet</td>" +
						"<td>Izmeni</td>" +
						"<td>Obrisi</td>" +
						"</th>");*/
				
				//$("#auctionTable").append(header);
				
				for (var i = 0;i < response.length;i++) {
					bid = response[i];					
					bidTr = $("<tr></tr>");

					indexTd = $("<td>" + (i + 1) + "</td>");
					bidPriceTd = $("<td>" + bid.price + "</td>");
					dateTd = $("<td></td>");
					dateTd.append(new Date(bid.dateTime).toDateString());
					
					userTd = $("<td>" + bid.user.name + "</td>");
					auctionTd = $("<td>" + bid.auction.id + "</td>");

					bidTr.append(indexTd);
					bidTr.append(bidPriceTd);
					bidTr.append(dateTd);
					bidTr.append(userTd);
					bidTr.append(auctionTd);

					$("#bidTable").append(bidTr);
				}
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
	}
}

$("document").ready(function() {
	UserManager.loadUsers();
	ItemManager.loadItems();
	UserManager.hideAddForm();
	UserManager.hideEditForm();
	$("#newItemForm").hide();
	$("#editItemForm").hide();
	AuctionManager.loadAuctions();
	$("#newAuctionForm").hide();
	$("#editAuctionForm").hide();
	$("#personalDataForm").hide();
	$("#passwordForm").hide();
	BidManager.loadBids();
	
	$("#saveButton").click(function (e) {
		e.preventDefault();
		UserManager.saveUser();
	});
	
	$("#saveEditButton").click(function (e) {
		e.preventDefault();
		UserManager.saveEditUser();
	});
	
	$("#addUserButton").click(function (e) {
		e.preventDefault();
		$("#addNewForm").show();
	});
	
	$("#addItemButton").click(function (e) {
		e.preventDefault();
		$("#newItemForm").show();
	});
	
	$("#itemSaveButton").click(function (e) {
		e.preventDefault();
		ItemManager.saveItem();
	});
	
	$("#editItemSaveButton").click(function (e) {
		e.preventDefault();
		ItemManager.saveEditItem();
	});
	
	$("#auctionSaveButton").click(function (e) {
		e.preventDefault();
		AuctionManager.saveAuction();
	});
	
	$("#editAuctionSaveButton").click(function (e) {
		e.preventDefault();
		AuctionManager.saveEditAuction();
	});
	
	$("#addAuctionButton").click(function (e) {
		e.preventDefault();
		$.ajax({
			url: "http://localhost:8080/api/users",
			dataType: "json",
			success: function(response) {
				for (i = 0;i < response.length;i++) {
					var user = response[i];
					$("#auctionUserList").append("<li><a href='javascript:AuctionManager.setAuctionUser(" + user.id + ")'>" + user.name + "</a></li>");
				}
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
		
		$.ajax({
			url: "http://localhost:8080/api/items",
			dataType: "json",
			success: function(response) {
				for (i = 0;i < response.length;i++) {
					var item = response[i];
					$("#auctionItemsList").append("<li><a href='javascript:AuctionManager.setAuctionItem(" + item.id + ")'>" + item.name + "</a></li>");
				}
			},
			error: function(request, options, error) {
				console.log(error);
			}
		});
		$("#newAuctionForm").show();
	});
	
	$("#editPersonalData").click(function (e) {
		e.preventDefault();
		$("#personalDataForm").show();
		$("#editPersonalData").hide();
		$.ajax({
			url: UserManager.basePath() + "/" + sessionStorage.getItem("loggedInId"),
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
			url: UserManager.basePath() + "/" + sessionStorage.getItem("loggedInId"),
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
			url: UserManager.basePath() + "/" + sessionStorage.getItem("loggedInId"),
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
			url: UserManager.basePath() + "/" + sessionStorage.getItem("loggedInId"),
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
	
	$("#logout").click(function (e) {
		sessionStorage.setItem("loggedInId", null);
		window.location.replace("file:///home/rade/Documents/OSA/Vezbe06/web/login.html");
	});
});