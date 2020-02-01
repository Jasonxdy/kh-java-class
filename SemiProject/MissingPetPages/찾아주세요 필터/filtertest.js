function genRnd(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
}
 
// Removes an Item From Selected Items and Deselects
function removeItem(id) {
    var multi_select = $('#access_multi_select-' + id.split('-')[0]);
    var selected_item = multi_select.find('[data-val="' + id + '"]');
    var item = multi_select.find('[id="' + id + '"]');
 
    item.prop('checked', false);
    selected_item.remove();
 
    if (multi_select.find('.selected-items > .item').length < 1) {
        multi_select.find('.selected-items > .placeholder').show();
    }
}
 
function selectAll(id, multi_select) {
    var select = $('#access_multi_select-' + id);
    select.find('.dropdown').find('input').prop('checked', true);
 
    if (multi_select) {
        select.find('.placeholder').hide();
        select.find('.selected-items > .item').remove();
        select.find('.dropdown > .items').find('input').each(function() {
            var item = $(this);
            item.prop('checked', true);
 
            select.find('.selected-items').append(
                '<span class="item" data-val="' + item.attr('id') + '">' + item.parent().find('label').html() +
                '   <button type="button" onclick="removeItem($(this).parent().attr(\'data-val\'));">&times;</button>' +
                '</span>'
            );
        });
    } else {
        alert('Multi-Select option is off! \nPlease turn it on to select all.');
    }
}
 
function deselectAll(id) {
    var select = $('#access_multi_select-' + id);
    select.find('.dropdown').find('input').prop('checked', false);
 
    select.find('.selected-items > .placeholder').show();
    select.find('.selected-items > .item').remove();
}
 
(function($) {
    var settings, input, select, selectId, dropDown, selectedItems;
 
    var methods = {
        init: function(options) {
            settings = $.extend({
                multi_select: true,
                items: [],
                defaults: [],
                filter_text: 'Filter',
                rtl: false,
                case_sensitive: false
            }, options);
 
            input = $(this);
            selectId = genRnd(1000, 10000);
 
            input.css({
                'display': 'none',
                'position': 'absolute'
            });
 
            select = $(
                '<div class="access_multi_select ' + (settings.rtl ? 'rtl' : '') + '" id="access_multi_select-' + selectId + '">' +
                '   <div class="selected-items form-control">' +
                '      <span class="placeholder">' + input.attr('placeholder') + '</span>' +
                '      <button type="button" onclick="selectAll(' + selectId + ', ' + settings.multi_select + ')"></button>' +
                '      <button type="button" onclick="deselectAll(' + selectId + ')"></button>' +
                '   </div>' +
                '   <div class="dropdown form-control">' +
                '       <div class="filter">' +
                '           <input type="text" class="form-control" placeholder="' + settings.filter_text + '">' +
                '           <button type="button" onclick="$(this).parent().find(\'input\').val(\'\').focus()">&times;</button>' +
                '       </div>' +
                '       <div class="items"></div>' +
                '   </div>' +
                '</div>').insertAfter(input);
 
            dropDown = select.find('.dropdown');
            selectedItems = select.find('.selected-items');
 
            if (settings.defaults.length > 0) {
                selectedItems.find('.placeholder').hide();
            }
 
            if (!settings.multi_select && settings.defaults.length > 1) {
                alert('Multi-Select is off! \nPlease turn it on to select more than one item.');
 
                return;
            }
 
            settings.items.forEach(function(item) {
                select.find('.items').append(
                    '<div class="item">' +
                    '   <div class="custom-control custom-checkbox">' +
                    '       <input type="checkbox" class="custom-control-input" id="' + selectId + '-chbx-' + item['value'] + '" ' + (settings.defaults.includes(item['value']) ? 'checked' : '') + '>' +
                    '       <label class="custom-control-label ' + selectId + '-chbx-' + item['value'] + '" for="' + selectId + '-chbx-' + item['value'] + '">' + item['text'] + '</label>' +
                    '   </div>' +
                    '</div>'
                );
 
                if (settings.defaults.includes(item['value'])) {
                    // Selected Items
                    selectedItems.append(
                        '<span class="item" data-val="' + selectId + '-chbx-' + item['value'] + '">' + item['text'] +
                        '   <button type="button" onclick="removeItem($(this).parent().attr(\'data-val\'));">&times;</button>' +
                        '</span>'
                    );
                }
            });
 
            // Propagation Onclick Disable
            dropDown.click(function(e) {
                e.stopPropagation();
            });
 
            // Clicks anywhere Other than The Multi-select
            $(document).mouseup(function(e) {
                if (!select.is(e.target) && select.has(e.target).length === 0) {
                    selectedItems.removeClass('expand');
                    dropDown.removeClass('expand');
                }
            });
 
            // Dropdown On Click
            dropDown.find('.item').click(function(e) {
                e.stopPropagation();
                e.preventDefault();
 
                var item = $(this);
                var inputElem = item.find('input');
 
                if (selectedItems.find('.item').length < 1) {
                    selectedItems.find('.placeholder').hide();
                }
 
                if (inputElem.prop('checked')) {
                    selectedItems.find('[data-val="' + inputElem.attr('id') + '"]').remove();
 
                    inputElem.prop('checked', false);
 
                    if (selectedItems.find('.item').length < 1) {
                        selectedItems.find('.placeholder').show();
                    }
                } else {
 
                    if (!settings.multi_select) {
                        dropDown.find('.item input').prop('checked', false);
                        select.find('.selected-items > .item').remove();
                    }
 
                    inputElem.prop('checked', true);
 
                    select.find('.selected-items').append(
                        '<span class="item" data-val="' + inputElem.attr('id') + '">' + item.find('label').html() +
                        '   <button type="button" onclick="removeItem($(this).parent().attr(\'data-val\'));">&times;</button>' +
                        '</span>'
                    );
                }
            });
 
            // Expand Dropdown
            select.on('click', function() {
                dropDown.addClass('expand');
                selectedItems.addClass('expand');
            });
 
            // Filter Input Changes
            select.find('input[type="text"]').on('keyup focus', function() {
                var filter = $(this);
                var text = filter.val();
 
                if (settings.case_sensitive) {
                    dropDown.find('.item').each(function() {
                        var item = $(this);
                        if (item.html().includes(text)) {
                            item.show();
                        } else {
                            item.hide();
                        }
                    });
                } else {
                    dropDown.find('.item').each(function() {
                        var item = $(this);
                        if (item.html().toLowerCase().includes(text.toLowerCase())) {
                            item.show();
                        } else {
                            item.hide();
                        }
                    });
                }
            });
 
            return select;
        },
        fetch_country: function() {
            var items = [];
            select.find('.dropdown').find('input').each(function() {
                var item = $(this);
 
                if (item.prop('checked')) {
                    items.push(item.attr('id').split('-')[2]);
                }
            });
 
            return items;
        }
    };
 
    $.fn.check_multi_select = function(methodOrOptions) {
        if (methods[methodOrOptions]) {
            return methods[methodOrOptions].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof methodOrOptions === 'object' || !methodOrOptions) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + methodOrOptions + ' does not exist on jQuery.check_multi_select');
        }
    };
})(jQuery);