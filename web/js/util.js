if (!window.util) {
	window.util = {};
}


util.getService = function(url, callbackFunc, async) {
	if (!_.isBoolean(async)) {
		async = true;
	}

	$.ajax({
		type: "get",
		url: url,
		dataType: "json",
		async: async,
		success: function(data) {
			callbackFunc(data);
		},
		error: function(xhr, stat, err) {
			// 오류 메시지 표시 후 종료
			// showErrorMsg (overlay 사용);
			console.error(xhr, stat, err);
		}
	});
};

util.postService = function(url, data, callbackFunc, async) {
	if (!_.isBoolean(async)) {
		async = true;
	}

	util.showLoading();

	$.ajax({
		type: "post",
		url: url,
		timeout: 1000 * 60 * 30,
		contentType: "application/json",
		data: JSON.stringify(data),
		async: async,
		success: function(data) {
			util.hideLoading();

			callbackFunc(data);
		},
		error: function(xhr, stat, err) {
			util.hideLoading();
			// 오류 메시지 표시 후 종료
			// showErrorMsg (overlay 사용);
			console.error(xhr, stat, err);
		}
	});
};

util.putService = function(url, data, callbackFunc, async) {
	if (!_.isBoolean(async)) {
		async = true;
	}

	$.ajax({
		type: "put",
		url: url,
		contentType: "application/json",
		data: JSON.stringify(data),
		async: async,
		success: function(data) {
			var result = data;
			callbackFunc(data);
		},
		error: function(xhr, stat, err) {
			// 오류 메시지 표시 후 종료
			// showErrorMsg (overlay 사용);
			console.error(xhr, stat, err);
		}
	});
};

util.deleteService = function(url, callbackFunc, async) {
	if (!_.isBoolean(async)) {
		async = true;
	}

	$.ajax({
		type: "delete",
		url: url,
		dataType: "json",
		async: async,
		success: function(data) {
			var result = data;
			callbackFunc(data);
		},
		error: function(xhr, stat, err) {
			// 오류 메시지 표시 후 종료
			// showErrorMsg (overlay 사용);
			console.error(xhr, stat, err);
		}
	});
};

util.postFileService = function(url, data, callbackFunc, async) {
	if (!_.isBoolean(async)) {
		async = true;
	}

	util.showLoading();

	var form_data = new FormData();

	for ( var key in data ) {
		form_data.append(key, data[key]);
	}

	$.ajax({
		type: "post",
		url: url,
		timeout: 1000 * 60 * 30,
		contentType: false,
		processData: false,
		enctype: 'multipart/form-data',
		data: form_data,
		async: async,
		success: function(data) {
			util.hideLoading();

			var result = data;
			callbackFunc(data);
		},
		error: function(xhr, stat, err) {
			util.hideLoading();
			// 오류 메시지 표시 후 종료
			// showErrorMsg (overlay 사용);
			console.error(xhr, stat, err);
		}
	});
};

util.getHtml = function(url, callbackFunc, async) {
	if (!_.isBoolean(async)) {
		async = true;
	}

	util.showLoading();

	$.ajax({
		type: "get",
		url: url,
		dataType: "html",
		async: async,
		success: function(data) {
			util.hideLoading();

			callbackFunc(data);
		},
		error: function(xhr, stat, err) {
			util.hideLoading();

			console.error(xhr, stat, err);
		}
	});
};

util.createHtmlTemplate = function(templateId, data) {
	var fn_template = _.template($("#" + templateId).html());
	return fn_template(data);
}

util.showLoading = function() {
	top.$("#loading").show();
}

util.hideLoading = function() {
	top.$("#loading").hide();
}
