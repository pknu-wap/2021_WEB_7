$.fn.getVal = function() {
	var $el = $(this);
	var rValue = "";

	var tagName = $el.prop("tagName");
	var name = $el.prop("name");

	if (tagName === "INPUT" && ($el.data("format") === "date" || $el.data("format") === "yyyy-mm-dd" || $el.data("format") === "date-month" || $el.data("format") === "yyyy-mm")) {
		rValue = getDateValue(checkNull($el.val()));
	} else if ($el.prop("type") === "radio" || $el.prop("type") === "checkbox") {
		rValue = $("input[name='" + name + "']:checked").val();
	} else if (tagName === "DIV" || tagName === "SPAN") {
		rValue = $el.html();
	} else {
		rValue = checkNull($el.val(), "");
	}

	return rValue;
};

$.fn.setVal = function(value) {
	var $el = $(this);

	var tagName = $el.prop("tagName");
	var name = $el.prop("name");

	if (tagName === "INPUT" && ($el.data("format") === "date" || $el.data("format") === "yyyy-mm-dd" || $el.data("format") === "date-month" || $el.data("format") === "yyyy-mm")) {
		if (($el.data("format") === "date" || $el.data("format") === "yyyy-mm-dd") && value.length === 8) {
			//console.log("!1111111", value.replace(/(\d{4})(\d{2})(\d{2})/, '$1-$2-$3'));
			value = value.replace(/(\d{4})(\d{2})(\d{2})/, '$1-$2-$3');
		} else if (($el.data("format") === "date-month" || $el.data("format") === "yyyy-mm") && value.length === 6) {
			value = value.replace(/(\d{4})(\d{2})/, '$1-$2');
		} else {
			value = "";
		}

		$el.datepicker().datepicker('setDate', value);
	} else if ((tagName === "INPUT" || tagName === "TEXTAREA" || tagName === "SELECT") && $el.prop("type") !== "radio" && $el.prop("type") !== "checkbox") {
		if ($el.data("format") === "date") {
			$el.val(setDateFormat(value));
		} else {
			$el.val(value);
		}
	} else if (tagName === "INPUT" && ($el.prop("type") === "radio" || $el.prop("type") === "checkbox")) {
		$("input:radio[name=" + name + "]:input[value='" + value + "']").prop("checked", true);
	} else if (tagName === "DIV" || tagName === "SPAN") {
		$el.html(value);
	}

	return $el;
};

$.fn.getData = function() {
	var $el = $(this);
	var result = {};

	if ($el.prop("tagName") !== "FORM") {
		return result;
	}

	var $targets = $el.find("[data-field]");

	$targets.each(function(idx, obj) {
		var $target = $(obj);
		var field = $target.data("field");
		result[field] = $target.getVal();
	});

	return result;
};

$.fn.getFormData = function() {
	var $el = $(this);
	var result = new FormData();

	if ($el.prop("tagName") !== "FORM") {
		return result;
	}

	var $targets = $el.find("[data-field]");

	$targets.each(function(idx, obj) {
		var $target = $(obj);
		var field = $target.data("field");
		var type = $target.attr('type');
		// document.getElementById('formElem')[0].files[0]
		if (type === "file") {
			result.append("file", $target[0].files[0]);
		} else {
			result.append(field, $target.getVal());
		}
	});

	return result;
};

$.fn.setData = function(data, opts) {
	var $el = $(this);

	if ($el.prop("tagName") !== "FORM") {
		return;
	}

	var $targets = $el.find("[data-field]");

	$targets.each(function(idx, obj) {
		$target = $(obj);

		var field = $target.data("field");

		if (isNull(field)) {
			return;
		} else {
			$target.setVal(checkNull(data[field]), "");
		}
	});
};

$.fn.clear = function() {
	var $el = $(this);

	if ($el.prop("tagName") !== "FORM") {
		return;
	}

	$el.get(0).reset();
};

$.fn.setSelectList = function(list, isTotal, totalVal) {
	//console.log("setSelectList", list);

	var $el = $(this);

	$el.empty();

	if (isEmpty(totalVal)) {
		totalVal = "";
	}

	if (isTotal) {
		var total = new Option("전체", totalVal);
		$el.append($(total));
	}

	_.each(list, function(item) {
		var opt = new Option(item["CODE_NM"], item["CODE"]);
		$el.append($(opt));
	})
};

$.fn.reset = function() {
	var $el = $(this);

	if ($el.prop("tagName") === "FORM") {
		$el.each(function(idx, obj) {
			obj.reset();
		});
	}
};

$.fn.checkRequired = function() {
	var $el = $(this);

	//console.log("$el.prop(\"tagName\")", $el.prop("tagName"))

	if ($el.prop("tagName") !== "FORM") {
		return true;
	}

	var $targets = $el.find("[data-field]");
	var isValid = true;

	$targets.each(function(idx, obj) {
		var $target = $(obj);

		//console.log($target, $target.getVal(), $target.getVal() === "");

		if ($target.prop("required") && $target.getVal() === "") {
			//console.log($target);
			$target.focus();
			isValid = false;
			return false;
		}
	});

	//console.log("isValid1", isValid);

	return isValid;
};

$.fn.disable = function() {
	var $el = $(this);

	if ($el.prop("tagName") !== "FORM") {
		return;
	}

	var $targets = $el.find("[data-field]");

	$targets.each(function(idx, obj) {
		var $target = $(obj).attr("disabled", "disabled");
	});
};

$.fn.enable = function() {
	var $el = $(this);

	if ($el.prop("tagName") !== "FORM") {
		return;
	}

	var $targets = $el.find("[data-field]");

	$targets.each(function(idx, obj) {
		var $target = $(obj).removeAttr("disabled");
	});
};


function test() {
	alert("1");
}