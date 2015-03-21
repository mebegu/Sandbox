using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Pex.Framework;
using System.Diagnostics.Contracts;
using Microsoft.Pex.Framework.Validation;
using Microsoft.Pex.Framework.Explorable;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.Pex.Framework.Generated;
using Microsoft.Pex.Framework.Exceptions;

namespace NatSet
{
    [PexClass]
    public partial class NatSet
    {
        private Boolean[] sm;
        private List<int> rest;
        private int max;

        [ContractInvariantMethod]
        private void invariant()
        {
            Contract.Invariant(this.sm != null);
            Contract.Invariant(this.sm.Length == 100);
            Contract.Invariant(this.rest != null);
            Contract.Invariant(Contract.ForAll(this.rest, x => x > 99));
            Contract.Invariant(this.rest.Count > 1 ? Contract.ForAll(1, this.rest.Count, i => (this.rest.ElementAt(i) >= this.rest.ElementAt(i-1)) ) : true);
            Contract.Invariant(max == findMax());
        }


        public NatSet()
        {
            sm = new Boolean[100];
            rest = new List<int>();
            max = -1;
        }

        public NatSet(int k)
        {
            sm = new Boolean[100];
            rest = new List<int>();
            max = -1;
            insert(k);
        }

        [PexMethod]
        [PexAllowedContractRequiresFailure]
        public void insert(int k)
        {
            Contract.Requires(k >= 0);
            Contract.Ensures(k<100 ? sm[k] : rest.Contains(k));
            Contract.Ensures(max == findMax());

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

        [PexMethod]
        [PexAllowedException(typeof(ElementNotFoundException))]
        [PexAllowedContractRequiresFailure]
        public void remove(int k)
        {
            Contract.Requires(k >= 0);
            Contract.Ensures(k < 100 ? !sm[k] : !rest.Contains(k));
            Contract.Ensures(max == findMax());

            if (k < 100)
            {
                if (!sm[k]) throw new ElementNotFoundException();

                sm[k] = false;
            }
            else
            {
                if (!rest.Contains(k)) throw new ElementNotFoundException();

                rest.Remove(k);
            }

            max = findMax();
        }

        [PexMethod]
        [PexAllowedContractRequiresFailure]
        public void union(NatSet other)
        {
            Contract.Requires(other != null);
            Contract.Requires(other != this);
            Contract.Ensures(Contract.OldValue(other) == other);
            Contract.Ensures(Contract.ForAll(0, this.sm.Length, 
                i => this.sm[i] ? (other.sm[i] || Contract.OldValue(this.sm)[i]) : (!other.sm[i] && !(Contract.OldValue(this.sm)[i]))));

            Contract.Ensures(
                (this.rest.Count > 0) ?
                Contract.ForAll(0, this.rest.Count,
                    i => (other.rest.Contains(this.rest.ElementAt(i)) || Contract.OldValue(this.rest).Contains(this.rest.ElementAt(i))))
                : Contract.OldValue(this.rest).Count == 0 && other.rest.Count == 0);

            for (int i = 0; i < other.sm.Length; i++)
                if (other.sm[i]) insert(i);

            for (int i = 0; i < other.rest.Count; i++)
                insert(other.rest.ElementAt(i));


        }

        [PexMethod]
        [PexAllowedContractRequiresFailure]
        public void intersect(NatSet other)
        {
            Contract.Requires(other != null);
            Contract.Requires(other != this);
            Contract.Ensures(Contract.OldValue(other) == other);
            Contract.Ensures(Contract.ForAll(0, this.sm.Length,
                i => this.sm[i] ? (other.sm[i] && Contract.OldValue(this.sm)[i]) : (!other.sm[i] || !Contract.OldValue(this.sm)[i])));
            
            Contract.Ensures(
                (this.rest.Count > 0) ?
                Contract.ForAll(0, this.rest.Count,
                    i => (other.rest.Contains(this.rest.ElementAt(i)) && Contract.OldValue(this.rest).Contains(this.rest.ElementAt(i)))) 
                : (Contract.OldValue(rest).Count > 0) ?
                Contract.ForAll(0, Contract.OldValue(this.rest).Count,
                i => (!other.rest.Contains(Contract.OldValue(this.rest).ElementAt(i)))) 
                : true);

            for (int i = 0; (i < this.sm.Length && i < other.sm.Length); i++)
                if ((other.sm[i] != this.sm[i]) && this.sm[i])
                    remove(i);


            for (int i = 0; i < this.rest.Count; i++)
                if (!other.rest.Contains(this.rest.ElementAt(i)))
                    remove(this.rest.ElementAt(i));

        }

        public int getMax()
        {
            return max;
        }

        [PexMethod]
        [PexAllowedContractRequiresFailure]
        private int findMax()
        {
            Contract.Requires(sm != null);
            Contract.Requires(rest != null);
            Contract.Ensures(rest.Count > 0 ? Contract.Result<int>() == rest.Max()
                : Contract.Result<int>() != -1 ? sm[Contract.Result<int>()] && Contract.ForAll(Contract.Result<int>()+1, sm.Length, i => !sm[i]) 
                : Contract.ForAll(0, sm.Length, i=> !sm[i]));


            if (rest.Count != 0) return rest.Max();

            for (int i = sm.Length - 1; i >= 0; i--)
                if (sm[i]) return i;

            return -1;
        }

        [PexMethod]
        [PexAllowedContractRequiresFailure]
        private void insertTest(int[] values)
        {
            Contract.Ensures(Contract.ForAll(0,values.Length, i=> values[i] < 100 ? this.sm[values[i]] : this.rest.Contains(values[i])));
            PexAssume.IsNotNullOrEmpty(values);
            PexAssume.TrueForAny(0, values.Length, i => (values[i] > 100));
            PexAssume.TrueForAny(0, values.Length, i => (values[i] < 100));

            for (int i = 0; i < values.Length; i++)
                insert(values[i]);

        }

        [PexMethod]
        [PexAllowedException(typeof(ElementNotFoundException))]
        [PexAllowedContractRequiresFailure]
        private void removeTest(int[] values)
        {
            Contract.Ensures(Contract.ForAll(0, values.Length, i => values[i] < 100 ? !this.sm[values[i]] : !this.rest.Contains(values[i])));
            PexAssume.IsNotNullOrEmpty(values);
            PexAssume.TrueForAny(0, values.Length, i => (values[i] > 100));
            PexAssume.TrueForAny(0, values.Length, i => (values[i] < 100));

            for (int i = 0; i < values.Length; i++)
                insert(values[i]);

            for (int i = 0; i < values.Length; i++)
                remove(values[i]);

        }

        [PexMethod]
        [PexAllowedContractRequiresFailure]
        public void unionFixedTest()
        {
            NatSet other = new NatSet();
            insert(20);
            insert(25);
            insert(105);
            insert(120);
            other.insert(20);
            other.insert(105);
            other.insert(115);

            this.union(other);
        }

        [PexMethod]
        [PexAllowedContractRequiresFailure]
        public void intersectFixedTest()
        {
            NatSet other = new NatSet();
            insert(20);
            insert(25);
            insert(105);
            insert(120);
            other.insert(20);
            other.insert(105);
            other.insert(115);

            this.intersect(other);

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
}
