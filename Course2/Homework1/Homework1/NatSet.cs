using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Diagnostics.Contracts;

namespace Homework1
{
    class NatSet
    {
        private Boolean[] sm;
        private List<int> rest;
        private int max;

        [ContractInvariantMethod]
        public void invariant()
        {
            Contract.Invariant(this.sm != null);
            Contract.Invariant(Contract.ForAll(rest, x => x > 99);
            //Contract.Invariant(); CHECK FOR rest IS SORTED
            //Contract.Invariant(); CHECK IF max IS CORRECT
        }

        public NatSet()
        {
            sm = new Boolean[99];
            rest = new List<int>();
            max = -1;
        }


        public NatSet(int k)
        {
            sm = new Boolean[99];
            rest = new List<int>();
            max = -1;
            insert(k);
        }


        //Set max
        public void insert(int k)
        {
            Contract.Requires(k != null);
            Contract.Ensures(sm[k] || rest.Contains(k));

            if (k < 100)
            {
                sm[k] = true;
            }
            else
            {
                if (rest.Contains(k)) return;

                rest.Add(k);
                rest.Sort();
            }

        }


        //Test if exception works correctly
        //Reset max
        public void remove(int k)
        {
            Contract.Requires(k != null);
            Contract.Ensures(!sm[k] && !rest.Contains(k));

            if (k < 100)
            {
                if (sm[k]) throw new ElementNotFoundException();

                sm[k] = false;
            }
            else
            {
                if (!rest.Contains(k)) throw new ElementNotFoundException();

                rest.Remove(k);
            }
        }

        public void union(NatSet other)
        {

        }

        public void intersect(NatSet other)
        {

        }

    }


    public class ElementNotFoundException : System.ApplicationException
    {
        public ElementNotFoundException() { }
        public ElementNotFoundException(string message) { }
        public ElementNotFoundException(string message, System.Exception inner) { }

        // Constructor needed for serialization 
        // when exception propagates from a remoting server to the client.
        protected ElementNotFoundException(System.Runtime.Serialization.SerializationInfo info,
            System.Runtime.Serialization.StreamingContext context) { }
    }
}
