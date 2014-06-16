/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

	import entities.Maginfo;
	import java.util.Collection;
	import javax.ws.rs.Path;
	import javax.ws.rs.GET;
	import javax.ws.rs.POST;
	import javax.ws.rs.Produces;
	import javax.ws.rs.Consumes;
	import javax.ws.rs.PathParam;
	import javax.ws.rs.QueryParam;
	import javax.ws.rs.DefaultValue;
	import javax.ws.rs.core.Response;
	import javax.ws.rs.core.Context;
	import javax.ws.rs.core.UriInfo;
	import javax.persistence.EntityManager;
	import converter.MaginfoesConverter;
	import converter.MaginfoConverter;
	import com.sun.jersey.api.core.ResourceContext;

	/**
	 *
	 * @author EUCLIDES
	 */
	@Path("/maginfoes/")
	public class MaginfoesResource {
		@Context
		protected ResourceContext resourceContext;
		@Context
		protected UriInfo uriInfo;

		/** Creates a new instance of MaginfoesResource */
		public MaginfoesResource() {
		}

		/**
		 * Get method for retrieving a collection of Maginfo instance in XML format.
		 *
		 * @return an instance of MaginfoesConverter
		 */
		@GET
		@Produces({"application/xml", "application/json"})
		public MaginfoesConverter get(@QueryParam("start")
				@DefaultValue("0") int start, @QueryParam("max")
				@DefaultValue("10") int max, @QueryParam("expandLevel")
				@DefaultValue("1") int expandLevel, @QueryParam("query")
				@DefaultValue("SELECT e FROM Maginfo e") String query) {
			PersistenceService persistenceSvc = PersistenceService.getInstance();
			try {
				System.out.println(" start " + start);
				System.out.println(" max " + max);
				System.out.println(" query " + query);
				persistenceSvc.beginTx();
				return new MaginfoesConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
			} finally {
				persistenceSvc.commitTx();
				persistenceSvc.close();
			}
		}

		/**
		 * Post method for creating an instance of Maginfo using XML as the input format.
		 *
		 * @param data an MaginfoConverter entity that is deserialized from an XML stream
		 * @return an instance of MaginfoConverter
		 */
		@POST
		@Consumes({"application/xml", "application/json"})
		public Response post(MaginfoConverter data) {
			PersistenceService persistenceSvc = PersistenceService.getInstance();
			try {
				persistenceSvc.beginTx();
				EntityManager em = persistenceSvc.getEntityManager();
				Maginfo entity = data.resolveEntity(em);
				createEntity(data.resolveEntity(em));
				persistenceSvc.commitTx();
				return Response.created(uriInfo.getAbsolutePath().resolve(entity.getIssueId() + "/")).build();
			} finally {
				persistenceSvc.close();
			}
		}

		/**
		 * Returns a dynamic instance of MaginfoResource used for entity navigation.
		 *
		 * @return an instance of MaginfoResource
		 */
		@Path("{issueId}/")
		public MaginfoResource getMaginfoResource(@PathParam("issueId") Integer id) {
			MaginfoResource maginfoResource = resourceContext.getResource(MaginfoResource.class);
			maginfoResource.setId(id);
			return maginfoResource;
		}

		/**
		 * Returns all the entities associated with this resource.
		 *
		 * @return a collection of Maginfo instances
		 */
		protected Collection<Maginfo> getEntities(int start, int max, String query) {
			EntityManager em = PersistenceService.getInstance().getEntityManager();
			return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
		}

		/**
		 * Persist the given entity.
		 *
		 * @param entity the entity to persist
		 */
		protected void createEntity(Maginfo entity) {
			EntityManager em = PersistenceService.getInstance().getEntityManager();
			em.persist(entity);
		}
	}
