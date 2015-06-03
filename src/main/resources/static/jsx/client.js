React.render(<Cart />, document.getElementById('cart'));
$.get('/products',function(data){
	React.render(<ProductsList products={data._embedded.products} />, document.getElementById('products-list'));
});