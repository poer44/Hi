(function ($) {

  function Tab(elem) {
    var self = this;

    this.elem = elem;
    this.tab_item = this.elem.find(".tab").children();
    this.tab_cont = this.elem.find(".tab-cont");
    this.index = 0;

    this.tab_item.click(function () {
      self.index = $(this).index();

      self.tab();
    });

    // console.log($(".tab").children());
  };

  Tab.prototype = {
    tab: function () {
      this.tab_item.removeClass("active");
      this.tab_cont.css({display: "none"});
      this.tab_item.eq(this.index).addClass("active");
      this.tab_cont.eq(this.index).css({display: "block"});
    }
  };

  Tab.int = function (elems) {
    var self = this;

    elems.each(function () {
      new self($(this));
    });
  };

  window["Tab"] = Tab;

})(jQuery);