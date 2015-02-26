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
            Contract.Invariant(isListSorted(rest)); 
            Contract.Invariant(this.max == findMax(sm, rest));
        }

        public NatSet()
        {
            sm = new Boolean[100];
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


        public void insert(int k)
        {
            Contract.Requires(k != null);
            Contract.Requires(k >= 0);
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

            if (k > max) max = k;

        }


        //Test if exception works correctly
        //Reset max
        public void remove(int k)
        {
            Contract.Requires(k != null);
            Contract.Requires(k >= 0);
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

            max = findMax(sm, rest);
        }

        public void union(NatSet other)
        {
            
        }

        public void intersect(NatSet other)
        {

        }

        private Boolean isListSorted(List<int> lst)
        {
            for (int i = 0; i < lst.Capacity-1; i++)
            {
                if (lst.ElementAt(i) > lst.ElementAt(i + 1)) return false;
            }

            return true;
        }

        private int findMax(bool[] array, List<int> lst)
        {
            if (lst.Capacity != 0) return lst.ElementAt(lst.Capacity-1);

            for (int i = array.Length - 1; i >= 0; i--)
            {
                if (array[i]) return i;
            }

            return -1;
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
