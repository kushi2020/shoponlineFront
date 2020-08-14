<div class="container">
	<div class="row">
		<div class="col-md-3">
			<%@include file="./shared/sidebar.jsp"%>
		</div>
		<!-- to display actual products -->
		<div class="col-md-9">
			<!-- Added breadcrum component -->
			<div class="row">
				<div class="col-xs-12">
					<c:if test="${userClickAllProducts == true}">
						<script>
							window.categoryId = '';
						</script>
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">All Products</li>
						</ol>
					</c:if>
					<c:if test="${userClickCategoryProducts == true}">
						<script>
							window.categoryId = '${category.id}';
						</script>


						<ol class="breadcrumb">

							<li><a href="${contextRoot}/home">Home</a></li>


							<li class="active">Category</li>
							<li class="active">${category.name}</li>
						</ol>
					</c:if>

					<!-- Product List Added -->

					<div class="row">
						<div class="col-xs-12">
							<div class="container-fluid">
								<table id="productListtable"
									class="table table-striped table-borderd">

									<thead>

										<tr>
											<th></th>
											<th>Name</th>
											<th>Brand</th>
											<th>Price</th>
											<th>Qty. Available</th>
											<th></th>
										</tr>
									</thead>
									<tfoot>

										<tr>
											<th></th>
											<th>Name</th>
											<th>Brand</th>
											<th>Price</th>
											<th>Qty. Available</th>
											<th></th>
										</tr>
									</tfoot>

								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>