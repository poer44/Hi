(function ($) {

  function Slider(opts) {
    var self = this;

    // 获取页面元素
    this.sliderBox = $("#sliderBox");
    this.slider = $(".slider");
    this.sliderItem = $(".slider-item");
    this.btn_prev = $(".btn-prev");
    this.btn_next = $(".btn-next");
    this.slider_title = $(".slider-title").find("p");
    this.slider_btn = $(".btn-item")

    // 设置各类属性
    this.sliderItem_len = this.sliderItem.length;
    this.curIndex = 0;
    this.otherIndex = 0;
    this.b_stop = true;
    this.timer = null;


    // 默认属性
    var defaults = {
      width: 500,
      height: 280,
      method: "leftRight",
      ifAutoPlay: false
    };

    this.options = $.extend(defaults, opts);

    // 初始化图片
    this.intSlider();
    this.picWidth = this.sliderItem.width();
    this.picHeight = this.sliderItem.height();


    // 每个小圆点点击
    this.slider_btn.click(function () {
      self.curIndex = $(this).index();
      self.otherIndex = $(this).index();

      self.setDot(self.curIndex);
      self.setCaption(self.curIndex);

      if (self.options.method === "upDown") {
        self.slider.animate({top: -self.picHeight * self.curIndex});
      }

      if (self.options.method === "leftRight") {
        self.slider.animate({left: -self.picWidth * self.curIndex});
      }

      if (self.options.method === "opacity") {
        self.setOp(self.curIndex, function () {});
      }
    });

    this.btn_prev.click(function () {
      self.prevClick();
    });

    this.btn_next.click(function () {
      self.nextClick();
    });

    // 自动播放
    if (this.options.ifAutoPlay) {
      self.autoPlay();  
    }
  };

  Slider.prototype = {
    setOp: function (index, fn) { // 改变透明度
      this.sliderItem.fadeOut("slow");
      this.sliderItem.eq(index).fadeIn("slow");

      fn();
    },

    autoPlay: function () { // 自动播放
      var self = this;

      this.timer = setInterval(function () {
        self.nextClick();
      }, 3000);

      this.sliderBox.hover(function () {
        clearInterval(self.timer);
      }, function () {
        self.timer = setInterval(function () {
          self.nextClick();
        }, 3000);
      });
    },

    prevClick: function () { // 切换到上一张
      var self = this;

      if (this.options.method === "upDown") {
        if (this.b_stop) {
          this.b_stop = false;
        
          if (this.curIndex == 0) {
            this.curIndex = this.sliderItem_len - 1;

            this.sliderItem.eq(this.sliderItem_len - 1)
                           .css({
                                 position: "relative",

                                 // 这是放在所有图片的前面
                                 top: -this.picHeight * this.sliderItem_len
                               });
          } else {
            this.curIndex --;
          }

          this.otherIndex --;

          this.setDot(this.curIndex);
          this.setCaption(this.curIndex);
          this.slider.animate({top: -this.picHeight * this.otherIndex}, function () {
            if (self.curIndex == self.sliderItem_len - 1) {
              self.otherIndex = self.sliderItem_len - 1;

              self.sliderItem.eq(self.sliderItem_len - 1).css({position: "static"});
              self.slider.css({top: -self.picHeight * (self.sliderItem_len - 1)});
            }

            self.b_stop = true;
          });
        }
      }

      if (this.options.method === "leftRight") {
        if (this.b_stop) {
          this.b_stop = false;
        
          if (this.curIndex == 0) {
            this.curIndex = this.sliderItem_len - 1;

            this.sliderItem.eq(this.sliderItem_len - 1)
                           .css({
                                 position: "relative",

                                 // 这是放在所有图片的前面
                                 left: -this.picWidth * this.sliderItem_len
                               });
          } else {
            this.curIndex --;
          }

          this.otherIndex --;

          this.setDot(this.curIndex);
          this.setCaption(this.curIndex);
          this.slider.animate({left: -this.picWidth * this.otherIndex}, function () {
            if (self.curIndex == self.sliderItem_len - 1) {
              self.otherIndex = self.sliderItem_len - 1;

              self.sliderItem.eq(self.sliderItem_len - 1).css({position: "static"});
              self.slider.css({left: -self.picWidth * (self.sliderItem_len - 1)});
            }

            self.b_stop = true;
          });
        }
      }

      if (this.options.method === "opacity") {
        if (this.b_stop) {
          this.b_stop = false;

          if (this.curIndex == 0) {
            this.curIndex = this.sliderItem_len - 1;
          } else {
            this.curIndex --;
          }

          this.setDot(this.curIndex);
          this.setCaption(this.curIndex);
          this.setOp(this.curIndex, function () {
            self.b_stop = true;
          });
        }
      }
    },

    nextClick: function () { // 切换到下一张
      var self = this;

      if (this.options.method === "upDown") {
        if (this.b_stop) {
          this.b_stop = false;
        
          if (this.curIndex == this.sliderItem_len - 1) {
            this.curIndex = 0;

            this.sliderItem.eq(0)
                           .css({
                                  position: "relative",

                                  // 这是放在所有图片的后面
                                  top: this.picHeight * this.sliderItem_len
                                });
          } else {
            this.curIndex ++;
          }

          self.otherIndex ++;

          this.setDot(this.curIndex);
          this.setCaption(this.curIndex);
          this.slider.animate({top: -this.picHeight * this.otherIndex}, function () {
            if (self.curIndex == 0) {
              self.otherIndex = 0;

              self.slider.css({top: 0});
              self.sliderItem.eq(0).css({position: 'static'});
            }

            self.b_stop = true;
          });
        }
      }

      if (this.options.method === "leftRight") {
        if (this.b_stop) {
          this.b_stop = false;
        
          if (this.curIndex == this.sliderItem_len - 1) {
            this.curIndex = 0;

            this.sliderItem.eq(0)
                           .css({
                                  position: "relative",

                                  // 这是放在所有图片的后面
                                  left: this.picWidth * this.sliderItem_len
                                });
          } else {
            this.curIndex ++;
          }

          self.otherIndex ++;

          this.setDot(this.curIndex);
          this.setCaption(this.curIndex);
          this.slider.animate({left: -this.picWidth * this.otherIndex}, function () {
            if (self.curIndex == 0) {
              self.otherIndex = 0;

              self.slider.css({left: 0});
              self.sliderItem.eq(0).css({position: 'static'});
            }

            self.b_stop = true;
          });
        }
      }

      if (this.options.method === "opacity") {
        if (this.b_stop) {
          this.b_stop = false;
        
          var self = this;

          if (this.curIndex == this.sliderItem_len - 1) {
            this.curIndex = 0;
          } else {
            this.curIndex ++;
          }

          this.setDot(this.curIndex);
          this.setCaption(this.curIndex);
          this.setOp(this.curIndex, function () {
            self.b_stop = true;
          });
        }
      }
    },

    setCaption: function (index) { // 设置标题
      this.slider_title.text(this.sliderItem.eq(index).attr("data-title"));
    },

    setDot: function (index) { // 设置小圆点
      this.slider_btn.removeClass("btn-item__active");
      this.slider_btn.eq(index).addClass("btn-item__active");
    },

    intSlider: function () { // 初始化设置
      this.sliderBox.css({width: this.options.width, height: this.options.height});
      this.sliderItem.css({width: this.options.width, height: this.options.height});

      if (this.options.method === "upDown") {
        this.sliderItem.css({display: "block"});

        this.slider.css({
          width: this.options.width, 
          height: this.sliderItem_len * this.options.height
        });
      }

      if (this.options.method === "leftRight") {
        this.sliderItem.css({float: "left"});

        this.slider.css({
          width: this.sliderItem_len * this.options.width, 
          height: this.options.height
        });
      }

      if (this.options.method === "opacity") {
        $(".slider, .slider-item").css({position: "absolute"});
        $(".slider-item").css({display: "none"}).first().css({display: "block"});
      }
    }
  };

  window["Slider"] = Slider;

})(jQuery);