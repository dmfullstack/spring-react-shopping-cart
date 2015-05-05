var ProductsList = React.createClass({
	getInitialState: function () {
        return this.props;
    },
    componentDidMount: function() {
        var eventSource = new EventSource("/sse/updates");
        var self = this;
        eventSource.onmessage = function(e) {
            var product = JSON.parse(e.data);
            var products = self.state.products;
            var newProducts = products.concat([product]);
            self.setState({products: newProducts});
        };
    },
    render: function() {
        var products = this.props.products.map(function(product) {
            return (
              <li key={product.id}>
                <Product data={product} />
              </li>
            )
        });

        return (
          <div id="products-list" className="products-list">
	          <ul className="clearfix">
	            {products}
	          </ul>
          </div>
        );
    }
});