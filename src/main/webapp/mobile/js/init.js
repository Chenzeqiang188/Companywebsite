    (function (doc, win) {
      var docEl = doc.documentElement,
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize', windw="",
        recalc = function () {
          var clientWidth = docEl.clientWidth;
          if (!clientWidth) return;
            if(windw==clientWidth)
                return;
            else
                windw=clientWidth;
          docEl.style.fontSize = 20 * (clientWidth / 640) + 'px';
        };
      if (!doc.addEventListener) return;
      win.addEventListener(resizeEvt, recalc, false);
      doc.addEventListener('DOMContentLoaded', recalc, false);
    })(document, window);   